package com.bjo.warehouse.dto.common;

import java.io.Serializable;

public class DetalleBodegaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long productId;
  private String nombre;
  private String marca;
  private String descripcion;
  private Double precio;
  private Long inventoryId;
  private Long cantidad;

  public DetalleBodegaDTO(Long productId, String nombre, String marca, String descripcion, Double precio, Long inventoryId, Long cantidad) {
    this.productId = productId;
    this.nombre = nombre;
    this.marca = marca;
    this.descripcion = descripcion;
    this.precio = precio;
    this.inventoryId = inventoryId;
    this.cantidad = cantidad;
  }

  /**
   * @return the productId
   */
  public Long getProductId() {
    return productId;
  }

  /**
   * @param productId the productId to set
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * @param nombre the nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  /**
   * @return the marca
   */
  public String getMarca() {
    return marca;
  }

  /**
   * @param marca the marca to set
   */
  public void setMarca(String marca) {
    this.marca = marca;
  }

  /**
   * @return the descripcion
   */
  public String getDescripcion() {
    return descripcion;
  }

  /**
   * @param descripcion the descripcion to set
   */
  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  /**
   * @return the precio
   */
  public Double getPrecio() {
    return precio;
  }

  /**
   * @param precio the precio to set
   */
  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  /**
   * @return the inventoryId
   */
  public Long getInventoryId() {
    return inventoryId;
  }

  /**
   * @param inventoryId the inventoryId to set
   */
  public void setInventoryId(Long inventoryId) {
    this.inventoryId = inventoryId;
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
