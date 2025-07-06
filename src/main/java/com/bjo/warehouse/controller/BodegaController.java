package com.bjo.warehouse.controller;

import com.bjo.warehouse.dto.ServiceRequestBodegaDTO;
import com.bjo.warehouse.dto.ServiceResponseBodegaDTO;
import com.bjo.warehouse.model.Bodega;
import com.bjo.warehouse.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse/v1/bodega")
public class BodegaController {

  @Autowired private BodegaRepository repository;

  // Crear un nuevo Bodega
  @PostMapping
  public ResponseEntity<Bodega> create(@RequestBody ServiceRequestBodegaDTO dto) {
    Bodega bodega = new Bodega(dto.getNombre());
    bodega.setCreatedAt(java.time.LocalDateTime.now());
    Bodega registrado = repository.save(bodega);
    return ResponseEntity.created(
            java.net.URI.create("/warehouse/v1/bodegas/" + registrado.getId()))
        .body(registrado);
  }

  // Obtener todos los Bodegas
  @GetMapping
  public ResponseEntity<ServiceResponseBodegaDTO> getAll() {
    ServiceResponseBodegaDTO service = new ServiceResponseBodegaDTO(repository.findAll());
    if (service.getBodegas().isEmpty()) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.ok(service);
    }
  }

  // Obtener un Bodega por id
  @GetMapping("/{id}")
  public ResponseEntity<Bodega> getById(@PathVariable Long id) {
    Optional<Bodega> Bodega = repository.findById(id);
    return Bodega.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  // Actualizar el nombre (Bodega) de un Bodega
  @PutMapping
  public ResponseEntity<Bodega> update(@RequestBody Bodega request) {
    Optional<Bodega> BodegaOptional = repository.findById(request.getId());
    if (BodegaOptional.isPresent()) {
      // Bodega Bodega = BodegaOptional.get();
      // Bodega.setBodega(request.getBodega());
      repository.save(request);
      return ResponseEntity.ok(request);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  // Borrar un Bodega por id
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
