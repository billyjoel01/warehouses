package com.bjo.warehouse.repository;

import com.bjo.warehouse.dto.common.DetalleBodegaDTO;
import com.bjo.warehouse.dto.common.PortalBodegaDTO;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.bjo.warehouse.dto.common.PortalDetallesTraslados;
import com.bjo.warehouse.dto.common.PortalInventarios;
import com.bjo.warehouse.dto.common.PortalTrasladoDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;

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

  public List<PortalBodegaDTO> getPortalBodegas() {
    return getPortalBodegas(null);
  }

  public List<PortalBodegaDTO> getPortalBodegas(Long bodegaId) {
    List<PortalBodegaDTO> bodegas;
    String sql =
        "select b.id as bodegaId, b.nombre as bodega, p.id as productId, p.nombre, p.marca, p.descripcion, p.precio, i.id as inventoryId, i.cantidad"
            + " from bodega b, inventario i, productos p"
            + " where b.id = i.bodega"
            + " and i.producto = p.id";
    if (bodegaId == null || bodegaId == 0) {
      sql += " order by 1, 3";
      @SuppressWarnings("unchecked")
      List<Object[]> rows = em.createNativeQuery(sql).getResultList();
      bodegas = mapToPortalBodegas(rows);
    } else {
      sql += " and b.id = ? order by 1, 3";
      @SuppressWarnings("unchecked")
      List<Object[]> rows = em.createNativeQuery(sql).setParameter(1, bodegaId).getResultList();
      bodegas = mapToPortalBodegas(rows);
    }
    return bodegas;
  }

  private List<PortalBodegaDTO> mapToPortalBodegas(List<Object[]> rows) {
    List<PortalBodegaDTO> bodegas = new ArrayList<>(rows.size());
    LinkedHashMap<Long, PortalBodegaDTO> m = new LinkedHashMap<>();
    for (Object[] r : rows) {
      Long idBodega = Long.valueOf(r[0].toString());
      if (m.containsKey(idBodega)) {
        m.get(idBodega).getProductos().add(buildDetalle(r));
      } else {
        List<DetalleBodegaDTO> l = new ArrayList<>();
        l.add(buildDetalle(r));
        PortalBodegaDTO b = new PortalBodegaDTO(idBodega, r[1].toString());
        b.setProductos(l);
        m.put(b.getId(), b);
      }
    }
    m.values()
        .forEach(
            b -> {
              bodegas.add(b);
            });
    return bodegas;
  }

  private DetalleBodegaDTO buildDetalle(Object[] r) {
    return new DetalleBodegaDTO(
        Long.valueOf(r[2].toString()),
        r[3].toString(),
        r[4].toString(),
        r[5].toString(),
        Double.valueOf(r[6].toString()),
        Long.valueOf(r[7].toString()),
        Long.valueOf(r[8].toString()));
  }

  /**
   * Devuelve una lista de traslados con la data formateada para el portal
   *
   * @return
   */
  public List<PortalTrasladoDTO> getPortalTraslados() {
    String sql =
        "select t.id, t.tracking_number, DATE_FORMAT(t.created_at, '%Y/%m/%d %H:%i:%s') AS created_at, o.nombre as Origen, d.nombre as Destino, e.estado "
            + "from traslados t, bodega o, bodega d, estados e "
            + "where t.origen = o.id "
            + "and t.destino = d.id "
            + "and t.estado = e.id "
            + "order by 1 desc";

    @SuppressWarnings("unchecked")
    List<Object[]> rows = em.createNativeQuery(sql).getResultList();
    List<PortalTrasladoDTO> lista = new ArrayList<>(rows.size());
    for (Object[] row : rows) {
      lista.add(
          new PortalTrasladoDTO(
              Long.parseLong(row[0].toString()),
              row[1].toString(),
              row[2].toString(),
              row[3].toString(),
              row[4].toString(),
              row[5].toString()
          ));
    }
    return lista;
  }
}
