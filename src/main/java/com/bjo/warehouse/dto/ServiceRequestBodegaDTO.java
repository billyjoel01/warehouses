package com.bjo.warehouse.dto;

public class ServiceRequestBodegaDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;
  
  private String nombre;

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
}