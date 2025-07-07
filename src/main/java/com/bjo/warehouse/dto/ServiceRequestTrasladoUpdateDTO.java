package com.bjo.warehouse.dto;


public class ServiceRequestTrasladoUpdateDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Long traslado;

  private Long estado;

  /**
   * @return the traslado
   */
  public Long getTraslado() {
    return traslado;
  }

  /**
   * @param traslado the traslado to set
   */
  public void setTraslado(Long traslado) {
    this.traslado = traslado;
  }

  /**
   * @return the estado
   */
  public Long getEstado() {
    return estado;
  }

  /**
   * @param estado the estado to set
   */
  public void setEstado(Long estado) {
    this.estado = estado;
  }
}