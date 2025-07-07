package com.bjo.warehouse.dto.common;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class TrasladoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private EstadoDTO estado;

  private BodegaDTO origen;

  private BodegaDTO destino;

  private String trackingNumber;

  private List<HistorialTrasladoDTO> historial;
  
  private List<DetalleDTO> detalles;

  public TrasladoDTO() {}

  public TrasladoDTO(
      Long id,
      LocalDateTime createdAt,
      LocalDateTime updatedAt,
      EstadoDTO estado,
      BodegaDTO origen,
      BodegaDTO destino,
      String trackingNumber,
      List<HistorialTrasladoDTO> historial) {
    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.estado = estado;
    this.origen = origen;
    this.destino = destino;
    this.trackingNumber = trackingNumber;
    this.historial = historial;
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
  public EstadoDTO getEstado() {
    return estado;
  }

  /**
   * @param estado the estado to set
   */
  public void setEstado(EstadoDTO estado) {
    this.estado = estado;
  }

  /**
   * @return the origen
   */
  public BodegaDTO getOrigen() {
    return origen;
  }

  /**
   * @param origen the origen to set
   */
  public void setOrigen(BodegaDTO origen) {
    this.origen = origen;
  }

  /**
   * @return the destino
   */
  public BodegaDTO getDestino() {
    return destino;
  }

  /**
   * @param destino the destino to set
   */
  public void setDestino(BodegaDTO destino) {
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

  /**
   * @return the historial
   */
  public List<HistorialTrasladoDTO> getHistorial() {
    return historial;
  }

  /**
   * @param historial the historial to set
   */
  public void setHistorial(List<HistorialTrasladoDTO> historial) {
    this.historial = historial;
  }

  /**
   * @return the detalles
   */
  public List<DetalleDTO> getDetalles() {
    return detalles;
  }

  /**
   * @param detalles the detalles to set
   */
  public void setDetalles(List<DetalleDTO> detalles) {
    this.detalles = detalles;
  }
}
