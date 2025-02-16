package com.application.rest.services.impl;

import com.application.rest.entities.Maker;
import com.application.rest.entities.Product;
import com.application.rest.entities.dto.ProductDTO;
import com.application.rest.entities.mappers.ProductMapper;
import com.application.rest.persistence.IProductDAO;
import com.application.rest.services.IMakerService;
import com.application.rest.services.IProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private IProductDAO productDAO;

  @Autowired
  private ProductMapper productMapper;

  @Autowired
  private IMakerService makerService;

  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }

  @Override
  public Optional<Product> findById(Long id) {
    return productDAO.findById(id);
  }

  @Override
  public List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
    return productDAO.findByPriceInRange(minPrice, maxPrice);
  }

  @Override
  public void save(Product product) {
    productDAO.save(product);
  }

  @Override
  public void delete(Product product) {
    product.logicalDelete();
    this.save(product);
  }

  @Override
  public void deleteById(Long id) {
    Optional<Product> productOptional = this.findById(id);
    if (productOptional.isPresent()) {
      Product product = productOptional.get();
      product.logicalDelete();
      this.save(product);
    } else {
      throw new RuntimeException("Maker not found");
    }
  }

  @Override
  public ProductDTO toDTO(Product product) {
    return productMapper.toDTO(product);
  }

  @Override
  public Product toEntity(ProductDTO productDTO) {
    Optional <Maker> makerOptional = this.makerService.findById(productDTO.getMakerId());
    if (makerOptional.isEmpty()) {
      throw new RuntimeException("Maker not found");
    }
    return productMapper.toEntity(productDTO, makerOptional.get());
  }
}
