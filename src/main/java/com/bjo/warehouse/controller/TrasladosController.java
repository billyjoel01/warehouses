package com.bjo.warehouse.controller;

import com.bjo.warehouse.dto.ServiceRequestTrasladoDTO;
import com.bjo.warehouse.dto.ServiceRequestTrasladoUpdateDTO;
import com.bjo.warehouse.dto.ServiceResponseTrasladoDTO;
import com.bjo.warehouse.dto.common.DetalleTrasladoDTO;
import com.bjo.warehouse.model.Traslado;
import com.bjo.warehouse.model.Bodega;
import com.bjo.warehouse.model.DetalleTraslado;
import com.bjo.warehouse.model.Estado;
import com.bjo.warehouse.model.HistoricoTraslado;
import com.bjo.warehouse.model.Producto;
import com.bjo.warehouse.repository.BodegaRepository;
import com.bjo.warehouse.repository.ProductoRepository;
import com.bjo.warehouse.repository.DetalleTrasladoRepository;
import com.bjo.warehouse.repository.EstadoRepository;
import com.bjo.warehouse.repository.HistoricoTrasladoRepository;
import com.bjo.warehouse.repository.TrasladoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse/v1/traslados")
public class TrasladosController {
  
  public static final Long ESTADO_PREPARACION = 1l;

  @Autowired private BodegaRepository bodegaRepository;
  @Autowired private ProductoRepository productRepository;
  @Autowired private DetalleTrasladoRepository detalleTrasladoRepository;
  @Autowired private EstadoRepository estadoRepository;
  @Autowired private HistoricoTrasladoRepository historicoTrasladoRepository;
  @Autowired private TrasladoRepository repository;

  // Crear un nuevo Traslado
  @PostMapping
  public ResponseEntity<Traslado> create(@RequestBody ServiceRequestTrasladoDTO dto) {
    Bodega origen = bodegaRepository.findById(dto.getOrigen()).orElseThrow();
    Bodega destino = bodegaRepository.findById(dto.getDestino()).orElseThrow();
    Traslado traslado = new Traslado();
    traslado.setOrigen(origen);
    traslado.setDestino(destino);
    traslado.setCreatedAt(LocalDateTime.now());
    // Puedes agregar más campos según tu modelo
    Traslado trasladoGuardado = repository.save(traslado);

    // Guardar los detalles
    for (DetalleTrasladoDTO detalleDTO : dto.getDetalles()) {
      Producto producto = productRepository.findById(detalleDTO.getProductId()).orElseThrow();
      DetalleTraslado detalle = new DetalleTraslado();
      detalle.setTraslado(trasladoGuardado);
      detalle.setProducto(producto);
      detalle.setCantidad(detalleDTO.getCantidad());
      detalle.setCreatedAt(LocalDateTime.now());
      detalleTrasladoRepository.save(detalle);
    }
    
    Estado estadoPreparacion = estadoRepository.findById(ESTADO_PREPARACION).orElseThrow();
    HistoricoTraslado historico = new HistoricoTraslado();
    historico.setCreatedAt(LocalDateTime.now());
    historico.setEstado(estadoPreparacion);
    historico.setTraslado(traslado);
    historicoTrasladoRepository.save(historico);

    return ResponseEntity.created(
            java.net.URI.create("/warehouse/v1/traslados/" + trasladoGuardado.getId()))
        .body(trasladoGuardado);
  }

  // Obtener todos los Traslados
  @GetMapping
  public ResponseEntity<ServiceResponseTrasladoDTO> getAll() {
    ServiceResponseTrasladoDTO service = new ServiceResponseTrasladoDTO(repository.findAll());
    if (service.getTraslados().isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(service);
    }
  }

  // Obtener un Traslado por id
  @GetMapping("/{id}")
  public ResponseEntity<Traslado> getById(@PathVariable Long id) {
    Optional<Traslado> Traslado = repository.findById(id);
    return Traslado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Actualizar el nombre (Traslado) de un Traslado
  @PutMapping
  public ResponseEntity<Traslado> update(@RequestBody ServiceRequestTrasladoUpdateDTO request) {
    Optional<Traslado> trasladoOptional = repository.findById(request.getTraslado());
    if (trasladoOptional.isPresent()) {
      Traslado traslado = trasladoOptional.get();
      Optional<Estado> estadoOptional = estadoRepository.findById(request.getEstado());
      if(estadoOptional.isPresent()) {
        Estado estado = estadoOptional.get();
        traslado.setEstado(estado);
        Traslado updated = repository.save(traslado);
        return ResponseEntity.ok(updated);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header("X-Error-meesage", "Estado no encontrado")
          .body(null);
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .header("X-Error-meesage", "Traslado no encontrado")
        .body(null);
    }
  }

  // Borrar un Traslado por id
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    if (repository.existsById(id)) {
      repository.deleteById(id);
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
