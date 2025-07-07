package com.bjo.warehouse.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventario")
public class Inventario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @ManyToOne(optional = false)
  @JoinColumn(name = "producto", nullable = false)
  private Producto producto;

  @ManyToOne(optional = false)
  @JoinColumn(name = "bodega", nullable = false)
  private Bodega bodega;

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
   * @return the producto
   */
  public Producto getProducto() {
    return producto;
  }

  /**
   * @param producto the producto to set
   */
  public void setProducto(Producto producto) {
    this.producto = producto;
  }

  /**
   * @return the bodega
   */
  public Bodega getBodega() {
    return bodega;
  }

  /**
   * @param bodega the bodega to set
   */
  public void setBodega(Bodega bodega) {
    this.bodega = bodega;
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
