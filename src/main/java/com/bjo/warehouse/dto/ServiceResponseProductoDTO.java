package com.bjo.warehouse.dto;

import com.bjo.warehouse.model.Producto;
import java.io.Serializable;
import java.util.List;

public class ServiceResponseProductoDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<Producto> producto;

  public ServiceResponseProductoDTO(List<Producto> producto) {
    this.producto = producto;
  }

  /**
   * @return the estados
   */
  public List<Producto> getProductos() {
    return producto;
  }

  /**
   * @param estados the estados to set
   */
  public void setProductos(List<Producto> estados) {
    this.producto = estados;
  }
}
