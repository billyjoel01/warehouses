package com.bjo.warehouse.dto;

import com.bjo.warehouse.model.Bodega;
import java.io.Serializable;
import java.util.List;

public class ServiceResponseBodegaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Bodega> bodegas;

  public ServiceResponseBodegaDTO(List<Bodega> bodega) {
    this.bodegas = bodega;
  }

  /**
   * @return the estados
   */
  public List<Bodega> getBodegas() {
    return bodegas;
  }

  /**
   * @param estados the estados to set
   */
  public void setBodegas(List<Bodega> estados) {
    this.bodegas = estados;
  }
}
