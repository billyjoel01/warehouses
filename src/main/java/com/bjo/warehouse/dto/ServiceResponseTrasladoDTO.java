package com.bjo.warehouse.dto;

import com.bjo.warehouse.dto.common.TrasladoDTO;
import java.io.Serializable;
import java.util.List;

public class ServiceResponseTrasladoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<TrasladoDTO> traslados;

  public ServiceResponseTrasladoDTO(List<TrasladoDTO> traslados) {
    this.traslados = traslados;
  }

  /**
   * @return the traslados
   */
  public List<TrasladoDTO> getTraslados() {
    return traslados;
  }

  /**
   * @param traslados the traslados to set
   */
  public void setTraslados(List<TrasladoDTO> traslados) {
    this.traslados = traslados;
  }
}
