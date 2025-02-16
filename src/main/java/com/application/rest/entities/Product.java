package com.application.rest.entities;

import com.application.rest.entities.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Product extends Persistent {
  @Column(name = "nombre")
  private String name;

  @Column(name = "precio")
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "fabricante_id", referencedColumnName = "id", nullable = false)
  @JsonIgnore
  private Maker maker;

  public Product(Long id, String name, BigDecimal price, Maker maker) {
    this.setId(id);
    this.name = name;
    this.price = price;
    this.maker = maker;
  }
}
