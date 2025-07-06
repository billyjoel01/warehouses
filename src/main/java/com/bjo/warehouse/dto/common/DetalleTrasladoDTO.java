package com.bjo.warehouse.dto.common;

import java.io.Serializable;

public class DetalleTrasladoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private long productId;

  private long cantidad;

  public DetalleTrasladoDTO() {}

  public DetalleTrasladoDTO(long productId, long cantidad) {
    this.productId = productId;
    this.cantidad = cantidad;
  }

  /**
   * @return the productId
   */
  public long getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(long productId) {
    this.productId = productId;
  }

  /**
   * @return the cantidad
   */
  public long getCantidad() {
    return cantidad;
  }

  /**
   * @param cantidad the cantidad to set
   */
  public void setCantidad(long cantidad) {
    this.cantidad = cantidad;
  }
}
