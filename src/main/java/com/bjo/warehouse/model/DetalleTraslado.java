package com.bjo.warehouse.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "detalles_traslados")
public class DetalleTraslado {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at")
  private LocalDateTime createdAt;
  
  @ManyToOne(optional = false)
  @JoinColumn(name = "traslado", nullable = false)
  private Traslado traslado;

  @ManyToOne(optional = false)
  @JoinColumn(name = "producto", nullable = false)
  private Producto producto;
  
  @Column(nullable = false)
  private Long cantidad;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the createdAt
   */
  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  /**
   * @param createdAt the createdAt to set
   */
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  /**
   * @return the traslado
   */
  public Traslado getTraslado() {
    return traslado;
  }

  public void setTraslado(Traslado traslado) {
    this.traslado = traslado;
  }

  public Producto getProducto() {
    return producto;
  }

  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  /**
   * @return the cantidad
   */
  public Long getCantidad() {
    return cantidad;
  }

  /**
   * @param cantidad the cantidad to set
   */
  public void setCantidad(Long cantidad) {
    this.cantidad = cantidad;
  }
}