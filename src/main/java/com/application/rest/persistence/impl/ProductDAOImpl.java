package com.application.rest.persistence.impl;

import com.application.rest.entities.Product;
import com.application.rest.persistence.IProductDAO;
import com.application.rest.repositories.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDAOImpl implements IProductDAO {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public List<Product> findAll() {
    return productRepository.findByActiveTrue();
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productRepository.findByIdAndActiveTrue(id);
  }

  @Override
  public List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
    return productRepository.findByPriceBetween(minPrice, maxPrice);
  }

  @Override
  public void save(Product product) {
    productRepository.save(product);
  }

  @Override
  public void deleteById(Long id) {
    productRepository.deleteById(id);
  }
}
