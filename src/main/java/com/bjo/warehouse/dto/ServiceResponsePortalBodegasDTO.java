package com.bjo.warehouse.dto;

import com.bjo.warehouse.dto.common.PortalBodegaDTO;
import java.io.Serializable;
import java.util.List;

public class ServiceResponsePortalBodegasDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<PortalBodegaDTO> bodegas;

  public ServiceResponsePortalBodegasDTO(List<PortalBodegaDTO> bodegas) {
    this.bodegas = bodegas;
  }

  /**
   * @return the bodegas
   */
  public List<PortalBodegaDTO> getBodegas() {
    return bodegas;
  }

  /**
   * @param bodegas the bodegas to set
   */
  public void setBodegas(List<PortalBodegaDTO> bodegas) {
    this.bodegas = bodegas;
  }
}
