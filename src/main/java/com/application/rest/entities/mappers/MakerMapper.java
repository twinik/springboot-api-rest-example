package com.application.rest.entities.mappers;

import com.application.rest.entities.Maker;
import com.application.rest.entities.dto.MakerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MakerMapper {

  @Autowired
  private ProductMapper ProductMapper;

  public MakerDTO toDTO(Maker maker) {
    return MakerDTO.builder()
        .id(maker.getId())
        .name(maker.getName())
        .productList(maker.getProductList().stream()
            .map(ProductMapper::toDTO).toList())
        .build();
  }

  public Maker toEntity(MakerDTO makerDTO) {
    return new Maker(makerDTO.getId(), makerDTO.getName());
  }
}
