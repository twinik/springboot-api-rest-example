package com.application.rest.repositories;

import com.application.rest.entities.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
  List<Product> findByActiveTrue();

  Optional<Product> findByIdAndActiveTrue(Long id);

  List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

  @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
  List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);
}
