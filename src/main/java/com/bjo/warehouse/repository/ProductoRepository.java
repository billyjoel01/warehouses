package com.bjo.warehouse.repository;

import com.bjo.warehouse.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
