package com.application.rest.entities.mappers;

import com.application.rest.entities.Maker;
import com.application.rest.entities.Product;
import com.application.rest.entities.dto.ProductDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ProductDTO toDTO(Product product) {
    return ProductDTO.builder()
        .id(product.getId())
        .name(product.getName())
        .price(product.getPrice())
        .makerId(product.getMaker().getId())
        .build();
  }

  public Product toEntity(ProductDTO productDTO, Maker maker) {
    return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice(), maker);
  }
}
