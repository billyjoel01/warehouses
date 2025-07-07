package com.bjo.warehouse.controller;

import com.bjo.warehouse.repository.ProductoRepository;
import com.bjo.warehouse.dto.ServiceRequestProductoDTO;
import com.bjo.warehouse.dto.ServiceResponseProductoDTO;
import com.bjo.warehouse.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse/v1/productos")
public class ProductosController {

  @Autowired private ProductoRepository repository;

  // Crear un nuevo Producto
  @PostMapping
  public ResponseEntity<Producto> create(@RequestBody ServiceRequestProductoDTO dto) {
    Producto prodducto =
        new Producto(dto.getNombre(), dto.getMarca(), dto.getDescripcion(), dto.getPrecio());
    prodducto.setCreatedAt(java.time.LocalDateTime.now());
    Producto registrado = repository.save(prodducto);
    return ResponseEntity.created(
            java.net.URI.create("/warehouse/v1/productos/" + registrado.getId()))
        .body(registrado);
  }

  // Obtener todos los Productos
  @GetMapping
  public ResponseEntity<ServiceResponseProductoDTO> getAll() {
    ServiceResponseProductoDTO service = new ServiceResponseProductoDTO(repository.findAll());
    if (service.getProductos().isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(service);
    }
  }

  // Obtener un Producto por id
  @GetMapping("/{id}")
  public ResponseEntity<Producto> getById(@PathVariable Long id) {
    Optional<Producto> Producto = repository.findById(id);
    return Producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Actualizar el nombre (Producto) de un Producto
  @PutMapping
  public ResponseEntity<Producto> update(@RequestBody Producto request) {
    Optional<Producto> ProductoOptional = repository.findById(request.getId());
    if (ProductoOptional.isPresent()) {
      // Producto Producto = ProductoOptional.get();
      // Producto.setProducto(request.getProducto());
      repository.save(request);
      return ResponseEntity.ok(request);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Borrar un Producto por id
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
