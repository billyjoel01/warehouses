package com.bjo.warehouse.repository;

import com.bjo.warehouse.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    java.util.List<Inventario> findByBodegaId(Long bodegaId);
}
