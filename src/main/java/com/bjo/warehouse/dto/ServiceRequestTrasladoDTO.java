package com.bjo.warehouse.dto;


import com.bjo.warehouse.dto.common.DetalleTrasladoDTO;
import java.util.List;

public class ServiceRequestTrasladoDTO implements java.io.Serializable {

  private static final long serialVersionUID = 1L;

  private Long origen;

  private Long destino;

  private List<DetalleTrasladoDTO> detalles;

  /**
   * @return the origen
   */
  public Long getOrigen() {
    return origen;
  }

  /**
   * @param origen the origen to set
   */
  public void setOrigen(Long origen) {
    this.origen = origen;
  }

  /**
   * @return the destino
   */
  public Long getDestino() {
    return destino;
  }

  /**
   * @param destino the destino to set
   */
  public void setDestino(Long destino) {
    this.destino = destino;
  }

  /**
   * @return the detalles
   */
  public List<DetalleTrasladoDTO> getDetalles() {
    return detalles;
  }

  /**
   * @param detalles the detalles to set
   */
  public void setDetalles(List<DetalleTrasladoDTO> detalles) {
    this.detalles = detalles;
  }
}