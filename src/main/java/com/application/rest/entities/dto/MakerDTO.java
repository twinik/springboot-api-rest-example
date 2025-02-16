package com.application.rest.entities.dto;

import com.application.rest.entities.Maker;
import com.application.rest.entities.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class MakerDTO {
  private Long id;
  private String name;
  private List<ProductDTO> productList;
}
