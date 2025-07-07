package com.bjo.warehouse.dto;

import com.bjo.warehouse.model.Inventario;
import java.io.Serializable;
import java.util.List;

public class ServiceResponseInventariosDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Inventario> inventarios;

  public ServiceResponseInventariosDTO(List<Inventario> inventarios) {
    this.inventarios = inventarios;
  }

  /**
   * @return the inventarios
   */
  public List<Inventario> getInventarios() {
    return inventarios;
  }

  /**
   * @param inventarios the inventarios to set
   */
  public void setInventarios(List<Inventario> inventarios) {
    this.inventarios = inventarios;
  }
}
