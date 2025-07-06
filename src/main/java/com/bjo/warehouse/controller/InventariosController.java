package com.bjo.warehouse.controller;

import com.bjo.warehouse.dto.ServiceRequestInventarioDTO;
import com.bjo.warehouse.dto.ServiceResponseInventariosDTO;
import com.bjo.warehouse.model.Bodega;
import com.bjo.warehouse.model.Inventario;
import com.bjo.warehouse.model.Producto;
import com.bjo.warehouse.repository.BodegaRepository;
import com.bjo.warehouse.repository.InventarioRepository;
import com.bjo.warehouse.repository.ProductoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/warehouse/v1/inventario")
public class InventariosController {

  @Autowired private InventarioRepository repository;
  @Autowired private ProductoRepository productoRepository;
  @Autowired private BodegaRepository bodegaRepository;

  // Crear un nuevo Inventario
  @PostMapping
  public ResponseEntity<Inventario> create(@RequestBody ServiceRequestInventarioDTO dto) {
    Optional<Producto> productoOptional = productoRepository.findById(dto.getProducto());
    if(productoOptional.isPresent()){
      Producto producto = productoOptional.get();
      Optional<Bodega> bodegaOptional = bodegaRepository.findById(dto.getBodega());
      if(bodegaOptional.isPresent()){
        Bodega bodega = bodegaOptional.get();
        Inventario inventario = new Inventario();
        inventario.setCreatedAt(LocalDateTime.now());
        inventario.setProducto(producto);
        inventario.setBodega(bodega);
        inventario.setCantidad(dto.getCantidad());
        Inventario registrado = repository.save(inventario);
        return ResponseEntity.created(
            java.net.URI.create("/warehouse/v1/inventario/" + registrado.getId()))
        .body(registrado);
      } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header("X-Error-meesage", "Producto no encontrado")
          .build();
      }
      
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header("X-Error-meesage", "Producto no encontrado")
          .body(null);
    }
  }

  // Obtener todos los Inventarios
  @GetMapping
  public ResponseEntity<ServiceResponseInventariosDTO> getAll() {
    ServiceResponseInventariosDTO service = new ServiceResponseInventariosDTO(repository.findAll());
    if (service.getInventarios().isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(service);
    }
  }

  // Obtener un Inventario por id
  @GetMapping("/{id}")
  public ResponseEntity<Inventario> getById(@PathVariable Long id) {
    Optional<Inventario> Inventario = repository.findById(id);
    return Inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }
  
  // Obtener todos los inventarios de una bodega específica
  @GetMapping("/bodega/{id}")
  public ResponseEntity<ServiceResponseInventariosDTO> getByBodega(@PathVariable Long id) {
    if (!bodegaRepository.existsById(id)) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .header("X-Error-Message", "Bodega no encontrada")
          .build();
    }
    List<Inventario> inventarios = repository.findByBodegaId(id);
    if (inventarios.isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(new ServiceResponseInventariosDTO(inventarios));
    }
  }

  // Actualizar el nombre (Inventario) de un Inventario
  @PutMapping
  public ResponseEntity<Inventario> update(@RequestBody Inventario request) {
    Optional<Inventario> InventarioOptional = repository.findById(request.getId());
    if (InventarioOptional.isPresent()) {
      // Inventario Inventario = InventarioOptional.get();
      // Inventario.setInventario(request.getInventario());
      repository.save(request);
      return ResponseEntity.ok(request);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Borrar un Inventario por id
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
