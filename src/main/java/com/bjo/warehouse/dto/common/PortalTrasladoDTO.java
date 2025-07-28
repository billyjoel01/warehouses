package com.bjo.warehouse.dto.common;

public class PortalTrasladoDTO {

  private long id;

  private String trackingNumber;

  private String createdAt;

  private String origen;
  
  private String destino;
  
  private String estado;

  public PortalTrasladoDTO(long id, String trackingNumber, String createdAt, String origen, String destino, String estado) {
    this.id = id;
    this.trackingNumber = trackingNumber;
    this.createdAt = createdAt;
    this.origen = origen;
    this.destino = destino;
    this.estado = estado;
  }

  /**
   * @return the id
   */
  public long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(long id) {
    this.id = id;
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
   * @return the createdAt
   */
  public String getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return the origen
   */
  public String getOrigen() {
    return origen;
  }

  /**
   * @param origen the origen to set
   */
  public void setOrigen(String origen) {
    this.origen = origen;
  }

  /**
   * @return the destino
   */
  public String getDestino() {
    return destino;
  }

  /**
   * @param destino the destino to set
   */
  public void setDestino(String destino) {
    this.destino = destino;
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
