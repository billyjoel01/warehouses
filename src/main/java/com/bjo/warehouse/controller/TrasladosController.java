package com.bjo.warehouse.controller;

import com.bjo.warehouse.dto.ServiceRequestTrasladoDTO;
import com.bjo.warehouse.dto.ServiceRequestTrasladoUpdateDTO;
import com.bjo.warehouse.dto.ServiceResponseTrasladoDTO;
import com.bjo.warehouse.dto.common.BodegaDTO;
import com.bjo.warehouse.dto.common.DetalleDTO;
import com.bjo.warehouse.dto.common.DetalleTrasladoDTO;
import com.bjo.warehouse.dto.common.EstadoDTO;
import com.bjo.warehouse.dto.common.HistorialTrasladoDTO;
import com.bjo.warehouse.dto.common.TrasladoDTO;
import com.bjo.warehouse.model.Traslado;
import com.bjo.warehouse.model.Bodega;
import com.bjo.warehouse.model.DetalleTraslado;
import com.bjo.warehouse.model.Estado;
import com.bjo.warehouse.model.HistoricoTraslado;
import com.bjo.warehouse.model.Inventario;
import com.bjo.warehouse.model.Producto;
import com.bjo.warehouse.repository.BodegaRepository;
import com.bjo.warehouse.repository.ProductoRepository;
import com.bjo.warehouse.repository.DetalleTrasladoRepository;
import com.bjo.warehouse.repository.EstadoRepository;
import com.bjo.warehouse.repository.HistoricoTrasladoRepository;
import com.bjo.warehouse.repository.InventarioRepository;
import com.bjo.warehouse.repository.TrasladoRepository;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse/v1/traslados")
public class TrasladosController {

  public static final Long ESTADO_PREPARACION = 1l;
  public static final Long ESTADO_RECEPCIONADO = 4l;

  @Autowired private BodegaRepository bodegaRepository;
  @Autowired private ProductoRepository productRepository;
  @Autowired private DetalleTrasladoRepository detalleTrasladoRepository;
  @Autowired private EstadoRepository estadoRepository;
  @Autowired private HistoricoTrasladoRepository historicoTrasladoRepository;
  @Autowired private InventarioRepository inventarioRepository;
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
    trasladoGuardado.setTrackingNumber(
        buildTrackingNumber(
            trasladoGuardado.getId(),
            trasladoGuardado.getCreatedAt().toString(),
            trasladoGuardado.getOrigen().getId(),
            trasladoGuardado.getDestino().getId()));
    trasladoGuardado = repository.save(trasladoGuardado);

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

  /**
   * Construye el tracking number basado en los parámetros
   *
   * @param id
   * @param createdAt
   * @param idOrigen
   * @param idDestino
   * @return
   */
  private String buildTrackingNumber(Long id, String createdAt, Long idOrigen, Long idDestino) {
    // Concatenar los parámetros en un solo string
    String raw = id + "-" + createdAt + "-" + idOrigen + "-" + idDestino;
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-256");
      byte[] hash = md.digest(raw.getBytes(java.nio.charset.StandardCharsets.UTF_8));
      // Convertir el hash a hexadecimal
      StringBuilder hexString = new StringBuilder();
      for (byte b : hash) {
        String hex = Integer.toHexString(0xff & b);
        if (hex.length() == 1) hexString.append('0');
        hexString.append(hex);
      }
      // Puedes devolver todo el hash o solo una parte para hacerlo más corto
      return hexString.toString().substring(0, 16).toUpperCase();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Error generando tracking number", e);
    }
  }

  // Obtener todos los Traslados
  @GetMapping
  public ResponseEntity<ServiceResponseTrasladoDTO> getAll() {
    List<Traslado> traslados = repository.findAll();
    if (traslados.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      List<TrasladoDTO> trasladosDTO = new ArrayList<>(traslados.size());
      for (Traslado traslado : traslados) {
        List<HistoricoTraslado> historial =
            historicoTrasladoRepository.findByTrasladoId(traslado.getId());
        List<HistorialTrasladoDTO> historialDTO = new ArrayList<>(historial.size());
        for (HistoricoTraslado h : historial) {
          historialDTO.add(
              new HistorialTrasladoDTO(
                  h.getId(),
                  h.getCreatedAt(),
                  h.getTraslado().getId(),
                  new EstadoDTO(h.getEstado().getId(), h.getEstado().getEstado())));
        }

        TrasladoDTO dto =
            new TrasladoDTO(
                traslado.getId(),
                traslado.getCreatedAt(),
                traslado.getUpdatedAt(),
                new EstadoDTO(traslado.getEstado().getId(), traslado.getEstado().getEstado()),
                new BodegaDTO(traslado.getOrigen().getId(), traslado.getOrigen().getNombre()),
                new BodegaDTO(traslado.getDestino().getId(), traslado.getDestino().getNombre()),
                traslado.getTrackingNumber(),
                historialDTO);
        setDetalles(dto);
        trasladosDTO.add(dto);
      }

      ServiceResponseTrasladoDTO service = new ServiceResponseTrasladoDTO(trasladosDTO);

      return ResponseEntity.ok(service);
    }
  }

  // Obtener un Traslado por id
  @GetMapping("/{id}")
  public ResponseEntity<TrasladoDTO> getById(@PathVariable Long id) {
    Optional<Traslado> trasladoOptional = repository.findById(id);
    if (trasladoOptional.isPresent()) {
      Traslado traslado = trasladoOptional.get();

      List<HistoricoTraslado> historial =
          historicoTrasladoRepository.findByTrasladoId(traslado.getId());
      List<HistorialTrasladoDTO> historialDTO = new ArrayList<>(historial.size());
      for (HistoricoTraslado h : historial) {
        historialDTO.add(
            new HistorialTrasladoDTO(
                h.getId(),
                h.getCreatedAt(),
                h.getTraslado().getId(),
                new EstadoDTO(h.getEstado().getId(), h.getEstado().getEstado())));
      }

      TrasladoDTO dto =
          new TrasladoDTO(
              traslado.getId(),
              traslado.getCreatedAt(),
              traslado.getUpdatedAt(),
              new EstadoDTO(traslado.getEstado().getId(), traslado.getEstado().getEstado()),
              new BodegaDTO(traslado.getOrigen().getId(), traslado.getOrigen().getNombre()),
              new BodegaDTO(traslado.getDestino().getId(), traslado.getDestino().getNombre()),
              traslado.getTrackingNumber(),
              historialDTO);
      setDetalles(dto);
      return ResponseEntity.ok(dto);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Actualizar el nombre (Traslado) de un Traslado
  @PutMapping
  public ResponseEntity<Traslado> update(@RequestBody ServiceRequestTrasladoUpdateDTO request) {
    Optional<Traslado> trasladoOptional = repository.findById(request.getTraslado());
    if (trasladoOptional.isPresent()) {
      Traslado traslado = trasladoOptional.get();
      Optional<Estado> estadoOptional = estadoRepository.findById(request.getEstado());
      if (estadoOptional.isPresent()) {
        Estado estado = estadoOptional.get();
        traslado.setEstado(estado);
        traslado.setUpdatedAt(LocalDateTime.now());
        Traslado updated = repository.save(traslado);

        HistoricoTraslado historico = new HistoricoTraslado();
        historico.setCreatedAt(LocalDateTime.now());
        historico.setEstado(estado);
        historico.setTraslado(updated);
        historicoTrasladoRepository.save(historico);

        if (Objects.equals(estado.getId(), ESTADO_RECEPCIONADO)) {
          List<DetalleTraslado> detalles =
              detalleTrasladoRepository.findByTrasladoId(traslado.getId());
          for (DetalleTraslado detalle : detalles) {
            Optional<Inventario> inventarioOrigenOpcional =
                inventarioRepository.findByBodegaIdAndProductoId(
                    detalle.getTraslado().getOrigen().getId(), detalle.getProducto().getId());
            if(inventarioOrigenOpcional.isPresent()){
              Inventario inventario = inventarioOrigenOpcional.get();
              inventario.setCantidad(inventario.getCantidad() - detalle.getCantidad());
              inventarioRepository.save(inventario);
            }
            Optional<Inventario> inventarioDestinoOpcional =
                inventarioRepository.findByBodegaIdAndProductoId(
                    detalle.getTraslado().getDestino().getId(), detalle.getProducto().getId());
            if(inventarioDestinoOpcional.isPresent()){
              Inventario inventario = inventarioDestinoOpcional.get();
              inventario.setCantidad(inventario.getCantidad() + detalle.getCantidad());
              inventarioRepository.save(inventario);
            }
          }

          //         inventarioRepository.save(estado);
          //         inventarioRepository.save(estado);
        }

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

  public void setDetalles(TrasladoDTO traslado) {
    List<DetalleTraslado> detalles = detalleTrasladoRepository.findByTrasladoId(traslado.getId());
    List<DetalleDTO> detallesDTO = new ArrayList<>(detalles.size());
    for (DetalleTraslado detalle : detalles) {
      detallesDTO.add(new DetalleDTO(detalle.getProducto(), detalle.getCantidad()));
    }
    traslado.setDetalles(detallesDTO);
  }
}
