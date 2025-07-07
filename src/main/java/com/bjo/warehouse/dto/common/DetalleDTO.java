package com.bjo.warehouse.dto.common;

import com.bjo.warehouse.model.Producto;
import java.io.Serializable;

public class DetalleDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Producto producto;

  private long cantidad;

  public DetalleDTO() {}

  public DetalleDTO(Producto producto, long cantidad) {
    this.producto = producto;
    this.cantidad = cantidad;
  }

  /**
   * @return the producto
   */
  public Producto getProducto() {
    return producto;
  }

  /**
   * @param producto the producto to set
   */
  public void setProducto(Producto producto) {
    this.producto = producto;
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
