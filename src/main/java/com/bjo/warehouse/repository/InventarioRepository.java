package com.bjo.warehouse.repository;

import com.bjo.warehouse.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    List<Inventario> findByBodegaId(Long bodegaId);

    // Obtener inventario por bodegaId y productoId
    Optional<Inventario> findByBodegaIdAndProductoId(Long bodegaId, Long productoId);
}
