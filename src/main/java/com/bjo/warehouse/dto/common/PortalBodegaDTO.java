package com.bjo.warehouse.dto.common;

import java.io.Serializable;
import java.util.List;

public class PortalBodegaDTO extends BodegaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<DetalleBodegaDTO> productos;

  public PortalBodegaDTO(Long id, String nombre) {
    super(id, nombre);
  }

  /**
   * @return the productos
   */
  public List<DetalleBodegaDTO> getProductos() {
    return productos;
  }

  /**
   * @param productos the productos to set
   */
  public void setProductos(List<DetalleBodegaDTO> productos) {
    this.productos = productos;
  }
}
