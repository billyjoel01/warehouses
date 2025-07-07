package com.bjo.warehouse.repository;

import com.bjo.warehouse.model.HistoricoTraslado;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoTrasladoRepository extends JpaRepository<HistoricoTraslado, Long> {

    List<HistoricoTraslado> findByTrasladoId(Long trasladoId);
}
