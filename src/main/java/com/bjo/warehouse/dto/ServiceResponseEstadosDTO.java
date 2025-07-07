package com.bjo.warehouse.dto;

import com.bjo.warehouse.model.Estado;
import java.io.Serializable;
import java.util.List;

public class ServiceResponseEstadosDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Estado> estados;

  public ServiceResponseEstadosDTO(List<Estado> estados) {
    this.estados = estados;
  }

  /**
   * @return the estados
   */
  public List<Estado> getEstados() {
    return estados;
  }

  /**
   * @param estados the estados to set
   */
  public void setEstados(List<Estado> estados) {
    this.estados = estados;
  }
}
