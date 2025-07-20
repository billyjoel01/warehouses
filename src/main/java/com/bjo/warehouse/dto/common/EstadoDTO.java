package com.bjo.warehouse.dto.common;

import java.io.Serializable;

public class EstadoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String estado;

  public EstadoDTO(Long id, String estado) {
    this.id = id;
    this.estado = estado;
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
   * @return the estado
   */
  public String getEstado() {
    return estado;
  }

  /**
   * @param estado the estado to set
   */
  public void setEstado(String estado) {
    this.estado = estado;
  }
}
