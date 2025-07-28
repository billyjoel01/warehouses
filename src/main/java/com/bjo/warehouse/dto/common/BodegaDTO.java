package com.bjo.warehouse.dto.common;

import java.io.Serializable;

public class BodegaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String nombre;

  public BodegaDTO(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * @param nombre nombre to set
   */
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}
