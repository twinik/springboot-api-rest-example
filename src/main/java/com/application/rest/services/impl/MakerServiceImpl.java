package com.application.rest.services.impl;

import com.application.rest.entities.Maker;
import com.application.rest.entities.dto.MakerDTO;
import com.application.rest.entities.mappers.MakerMapper;
import com.application.rest.persistence.IMakerDAO;
import com.application.rest.services.IMakerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MakerServiceImpl implements IMakerService {

  @Autowired
  private IMakerDAO makerDAO;

  @Autowired
  private MakerMapper makerMapper;

  @Override
  public List<Maker> findAll() {
    return this.makerDAO.findAll();
  }

  @Override
  public Optional<Maker> findById(Long id) {
    return this.makerDAO.findById(id);
  }

  @Override
  public Maker save(Maker maker) {
    this.makerDAO.save(maker);
    return maker;
  }

  @Override
  public void delete(Maker maker) {
    maker.logicalDelete();
    this.save(maker);
  }

  @Override
  public void deleteById(Long id) {
    Optional<Maker> makerOptional = this.findById(id);
    if (makerOptional.isPresent()) {
      Maker maker = makerOptional.get();
      maker.logicalDelete();
      this.save(maker);
    } else {
      throw new RuntimeException("Maker not found");
    }
  }

  @Override
  public MakerDTO toDTO(Maker maker) {
    return makerMapper.toDTO(maker);
  }

  @Override
  public Maker toEntity(MakerDTO makerDTO) {
    return makerMapper.toEntity(makerDTO);
  }
}
