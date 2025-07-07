package com.bjo.warehouse.dto;

import java.io.Serializable;

public class ServiceRequestEstadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
