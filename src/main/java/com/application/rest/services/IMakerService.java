package com.application.rest.services;

import com.application.rest.entities.Maker;
import com.application.rest.entities.dto.MakerDTO;
import java.util.List;
import java.util.Optional;

public interface IMakerService {
  List<Maker> findAll();
  Optional<Maker> findById(Long id);
  Maker save(Maker maker);
  void delete(Maker maker);
  void deleteById(Long id);
  MakerDTO toDTO(Maker maker);
  Maker toEntity(MakerDTO makerDTO);
}
