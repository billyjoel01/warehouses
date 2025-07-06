package com.bjo.warehouse.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "traslados")
public class Traslado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @ManyToOne(optional = false)
  @JoinColumn(name = "estado", nullable = false)
  private Estado estado;

  @ManyToOne(optional = false)
  @JoinColumn(name = "origen", nullable = false)
  private Bodega origen;

  @ManyToOne(optional = false)
  @JoinColumn(name = "destino", nullable = false)
  private Bodega destino;

  @Column(name = "tracking_number")
  private String trackingNumber;

  public Traslado() {
  }

  public Traslado(Bodega origen, Bodega destino) {
    this.origen = origen;
    this.destino = destino;
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
   * @return the updatedAt
   */
  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  /**
   * @param updatedAt the updatedAt to set
   */
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
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

  /**
   * @return the origen
   */
  public Bodega getOrigen() {
    return origen;
  }

  /**
   * @param origen the origen to set
   */
  public void setOrigen(Bodega origen) {
    this.origen = origen;
  }

  /**
   * @return the destino
   */
  public Bodega getDestino() {
    return destino;
  }

  /**
   * @param destino the destino to set
   */
  public void setDestino(Bodega destino) {
    this.destino = destino;
  }

  /**
   * @return the trackingNumber
   */
  public String getTrackingNumber() {
    return trackingNumber;
  }

  /**
   * @param trackingNumber the trackingNumber to set
   */
  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }
}
