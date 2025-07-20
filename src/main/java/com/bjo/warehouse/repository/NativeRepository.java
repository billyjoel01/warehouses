package com.bjo.warehouse.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.bjo.warehouse.dto.common.PortalDetallesTraslados;
import com.bjo.warehouse.dto.common.PortalInventarios;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class NativeRepository {

  @PersistenceContext private EntityManager em;

  public List<PortalDetallesTraslados> getPortalDetallesTraslados() {
    String sql =
        """
      SELECT e.id AS id, e.estado AS estado,
             COUNT(d.producto) AS productos,
             COALESCE(SUM(d.cantidad), 0) AS cantidad
      FROM estados e
      LEFT JOIN traslados t ON t.estado = e.id
      LEFT JOIN detalles_traslados d ON d.traslado = t.id
      GROUP BY e.id, e.estado
    """;

    @SuppressWarnings("unchecked")
    List<Object[]> rows = em.createNativeQuery(sql).getResultList();

    return rows.stream()
        .map(
            row ->
                new PortalDetallesTraslados(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    ((Number) row[2]).longValue(),
                    ((Number) row[3]).longValue()))
        .toList();
  }

  public List<PortalInventarios> getPortalInventarios() {
    String sql =
        """
      select b.id as ID, b.nombre as Bodega, count(producto) as Productos, sum(cantidad) as Cantidad
      from bodega b, inventario i
      where b.id = i.bodega
      group by ID, Bodega
    """;

    @SuppressWarnings("unchecked")
    List<Object[]> rows = em.createNativeQuery(sql).getResultList();

    return rows.stream()
        .map(
            row ->
                new PortalInventarios(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    ((Number) row[2]).longValue(),
                    ((Number) row[3]).longValue()))
        .toList();
  }
}
