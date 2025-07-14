package com.bjo.warehouse.repository;

import com.bjo.warehouse.model.Traslado;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrasladoRepository extends JpaRepository<Traslado, Long> {

  Optional<Traslado> findByTrackingNumber(String trackingNumber);

  long countByEstadoId(Long estadoId);

}
