package com.bjo.warehouse.dto;

public class ServiceRequestProductoDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  
  private String nombre;
  
  private String marca;
  
  private String descripcion;
  
  private double precio;

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
  public double getPrecio() {
    return precio;
  }

  /**
   * @param precio the precio to set
   */
  public void setPrecio(double precio) {
    this.precio = precio;
  }
}