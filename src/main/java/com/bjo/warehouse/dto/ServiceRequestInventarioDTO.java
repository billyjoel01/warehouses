package com.bjo.warehouse.dto;

public class ServiceRequestInventarioDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  
  private Long producto;
  
  private Long bodega;
  
  private Long cantidad;

  /**
   * @return the producto
   */
  public Long getProducto() {
    return producto;
  }

  /**
   * @param producto the producto to set
   */
  public void setProducto(Long producto) {
    this.producto = producto;
  }

  /**
   * @return the bodega
   */
  public Long getBodega() {
    return bodega;
  }

  /**
   * @param bodega the bodega to set
   */
  public void setBodega(Long bodega) {
    this.bodega = bodega;
  }

  /**
   * @return the cantidad
   */
  public Long getCantidad() {
    return cantidad;
  }

  /**
   * @param cantidad the cantidad to set
   */
  public void setCantidad(Long cantidad) {
    this.cantidad = cantidad;
  }
  
  
}