package com.bjo.warehouse.dto.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class HistorialTrasladoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private LocalDateTime createdAt;

  private Long idTraslado;

  private EstadoDTO estado;

  public HistorialTrasladoDTO(Long id, LocalDateTime createdAt, Long idTraslado, EstadoDTO estado) {
    this.id = id;
    this.createdAt = createdAt;
    this.idTraslado = idTraslado;
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
   * @return the createdAt
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return the idTraslado
   */
  public Long getIdTraslado() {
    return idTraslado;
  }

  /**
   * @param idTraslado the idTraslado to set
   */
  public void setIdTraslado(Long idTraslado) {
    this.idTraslado = idTraslado;
  }

  /**
   * @return the estado
   */
  public EstadoDTO getEstado() {
    return estado;
  }

  /**
   * @param estado the estado to set
   */
  public void setEstado(EstadoDTO estado) {
    this.estado = estado;
  }

  
}
