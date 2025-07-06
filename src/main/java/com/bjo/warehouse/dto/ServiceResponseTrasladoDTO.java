package com.bjo.warehouse.dto;

import com.bjo.warehouse.model.Traslado;
import java.io.Serializable;
import java.util.List;

public class ServiceResponseTrasladoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Traslado> traslados;

  public ServiceResponseTrasladoDTO(List<Traslado> traslados) {
    this.traslados = traslados;
  }

  /**
   * @return the traslados
   */
  public List<Traslado> getTraslados() {
    return traslados;
  }

  /**
   * @param traslados the traslados to set
   */
  public void setTraslados(List<Traslado> traslados) {
    this.traslados = traslados;
  }
}
