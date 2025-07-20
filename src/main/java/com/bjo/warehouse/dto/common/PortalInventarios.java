package com.bjo.warehouse.dto.common;

public class PortalInventarios {

  private long id;

  private String bodega;

  private long productos;

  private long cantidad;

  public PortalInventarios() {}

  public PortalInventarios(long id, String bodega, long productos, long cantidad) {
    this.id = id;
    this.bodega = bodega;
    this.productos = productos;
    this.cantidad = cantidad;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBodega() {
    return bodega;
  }

  public void setBodega(String bodega) {
    this.bodega = bodega;
  }

  public long getProductos() {
    return productos;
  }

  public void setProductos(long productos) {
    this.productos = productos;
  }

  public long getCantidad() {
    return cantidad;
  }

  public void setCantidad(long cantidad) {
    this.cantidad = cantidad;
  }
}
