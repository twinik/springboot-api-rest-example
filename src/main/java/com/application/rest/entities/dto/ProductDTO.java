package com.application.rest.entities.dto;

import com.application.rest.entities.Product;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
  private Long id;
  private String name;
  private BigDecimal price;
  private Long makerId;
}
