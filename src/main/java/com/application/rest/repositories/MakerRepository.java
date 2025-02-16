package com.application.rest.repositories;

import com.application.rest.entities.Maker;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakerRepository extends CrudRepository<Maker, Long> {
  List<Maker> findByActiveTrue();

  Optional<Maker> findByIdAndActiveTrue(Long id);
}
