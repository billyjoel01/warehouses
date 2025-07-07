package com.bjo.warehouse.controller;

import com.bjo.warehouse.model.Estado;
import com.bjo.warehouse.repository.EstadoRepository;
import com.bjo.warehouse.dto.ServiceRequestEstadoDTO;
import com.bjo.warehouse.dto.ServiceResponseEstadosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/warehouse/v1/estados")
public class EstadosController {

    @Autowired
    private EstadoRepository estadoRepository;

    // Crear un nuevo estado
    @PostMapping
    public ResponseEntity<Estado> createEstado(@RequestBody ServiceRequestEstadoDTO estadoDTO) {
        Estado estado = new Estado();
        estado.setEstado(estadoDTO.getEstado());
        estado.setCreatedAt(java.time.LocalDateTime.now());
        Estado savedEstado = estadoRepository.save(estado);
        return ResponseEntity.created(
                java.net.URI.create("/warehouse/v1/estados/" + savedEstado.getId())
        ).body(savedEstado);
    }

    // Obtener todos los estados
    @GetMapping
    public ResponseEntity<ServiceResponseEstadosDTO> getAllEstados() {
      ServiceResponseEstadosDTO service = new ServiceResponseEstadosDTO(estadoRepository.findAll());
      if(service.getEstados().isEmpty()){
        return ResponseEntity.noContent().build();
      } else {
        return ResponseEntity.ok(service);
      }
    }

    // Obtener un estado por id
    @GetMapping("/{id}")
    public ResponseEntity<Estado> getEstadoById(@PathVariable Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        return estado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar el nombre (estado) de un estado
    @PutMapping
    public ResponseEntity<Estado> updateEstado(@RequestBody Estado estadoRequest) {
        Optional<Estado> estadoOptional = estadoRepository.findById(estadoRequest.getId());
        if (estadoOptional.isPresent()) {
            Estado estado = estadoOptional.get();
            estado.setEstado(estadoRequest.getEstado());
            estadoRepository.save(estado);
            return ResponseEntity.ok(estado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Borrar un estado por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable Long id) {
        if (estadoRepository.existsById(id)) {
            estadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
