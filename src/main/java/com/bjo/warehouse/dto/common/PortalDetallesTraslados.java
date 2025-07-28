package com.bjo.warehouse.dto.common;

public class PortalDetallesTraslados {

  private long id;

  private String estado;

  private long productos;

  private long cantidad;

  public PortalDetallesTraslados() {}

  public PortalDetallesTraslados(long id, String estado, long productos, long cantidad) {
    this.id = id;
    this.estado = estado;
    this.productos = productos;
    this.cantidad = cantidad;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
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
