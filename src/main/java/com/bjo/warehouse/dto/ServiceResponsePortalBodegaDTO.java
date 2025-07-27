package com.bjo.warehouse.dto;

import com.bjo.warehouse.dto.common.PortalBodegaDTO;
import java.io.Serializable;

public class ServiceResponsePortalBodegaDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private PortalBodegaDTO bodega;

  public ServiceResponsePortalBodegaDTO(PortalBodegaDTO bodega) {
    this.bodega = bodega;
  }

  /**
   * @return the bodega
   */
  public PortalBodegaDTO getBodega() {
    return bodega;
  }

  /**
   * @param bodega the bodega to set
   */
  public void setBodega(PortalBodegaDTO bodega) {
    this.bodega = bodega;
  }
}
