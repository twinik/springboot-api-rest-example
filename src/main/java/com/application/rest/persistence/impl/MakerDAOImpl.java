package com.application.rest.persistence.impl;

import com.application.rest.entities.Maker;
import com.application.rest.persistence.IMakerDAO;
import com.application.rest.repositories.MakerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MakerDAOImpl implements IMakerDAO {

  @Autowired
  private MakerRepository makerRepository;

  @Override
  public List<Maker> findAll() {
    return (List<Maker>) makerRepository.findByActiveTrue();
  }

  @Override
  public Optional<Maker> findById(Long id) {
    return makerRepository.findByIdAndActiveTrue(id);
  }

  @Override
  public void save(Maker maker) {
    makerRepository.save(maker);
  }

  @Override
  public void deleteById(Long id) {
    makerRepository.deleteById(id);
  }
}
