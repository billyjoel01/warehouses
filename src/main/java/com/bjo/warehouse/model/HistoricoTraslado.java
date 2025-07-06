package com.bjo.warehouse.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_traslado")
public class HistoricoTraslado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @ManyToOne(optional = false)
  @JoinColumn(name = "traslado", nullable = false)
  private Traslado traslado;

  @ManyToOne(optional = false)
  @JoinColumn(name = "estado", nullable = false)
  private Estado estado;

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
   * @return the traslado
   */
  public Traslado getTraslado() {
    return traslado;
  }

  /**
   * @param traslado the traslado to set
   */
  public void setTraslado(Traslado traslado) {
    this.traslado = traslado;
  }

  /**
   * @return the estado
   */
  public Estado getEstado() {
    return estado;
  }

  /**
   * @param estado the estado to set
   */
  public void setEstado(Estado estado) {
    this.estado = estado;
  }
}
