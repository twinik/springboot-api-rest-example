package com.application.rest.controllers;

import com.application.rest.entities.dto.MakerDTO;
import com.application.rest.entities.dto.ProductDTO;
import com.application.rest.entities.Maker;
import com.application.rest.entities.Product;
import com.application.rest.services.IMakerService;
import com.application.rest.services.IProductService;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private static final String PRODUCT_BASE_URL = "/api/products";

  @Autowired
  private IProductService productService;

  @Autowired
  private IMakerService makerService;

  @GetMapping
  public ResponseEntity<List<ProductDTO>> findAll() {
    List<ProductDTO> productList = productService.findAll().stream()
        .map(productService::toDTO)
        .toList();
    return ResponseEntity.ok(productList);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
    Optional<Product> productOptional = productService.findById(id);
    if (productOptional.isPresent()) {
      Product product = productOptional.get();
      ProductDTO productDTO = productService.toDTO(product);
      return ResponseEntity.ok(productDTO);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/price-range")
  public ResponseEntity<List<ProductDTO>> findByPriceInRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
    List<ProductDTO> productList = productService.findByPriceInRange(minPrice, maxPrice).stream()
        .map(productService::toDTO)
        .toList();
    if (productList.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(productList);
  }


  @PostMapping
  public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws URISyntaxException {
    if (productDTO.getName().isBlank() || productDTO.getPrice() == null || productDTO.getMakerId() == null) {
      return ResponseEntity.badRequest().build();
    }
    Optional<Maker> makerOptional = makerService.findById(productDTO.getMakerId());
    if (makerOptional.isEmpty()) {
      return ResponseEntity.badRequest().body("El fabricante no existe");
    }
    Product product = Product.builder()
        .name(productDTO.getName())
        .price(productDTO.getPrice())
        .maker(makerOptional.get())
        .build();
    productService.save(product);
    productDTO.setId(product.getId());
    return ResponseEntity.created(new URI(PRODUCT_BASE_URL + "/" + product.getId())).body(productDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
    if (productDTO.getName().isBlank() || productDTO.getPrice() == null || productDTO.getMakerId() == null) {
      return ResponseEntity.badRequest().build();
    }
    Optional<Product> productOptional = productService.findById(id);
    if (productOptional.isPresent()) {
      Optional<Maker> makerOptional = makerService.findById(productDTO.getMakerId());
      if (makerOptional.isEmpty()) {
        return ResponseEntity.badRequest().body("El fabricante no existe");
      }
      Product product = productOptional.get();
      product.setName(productDTO.getName());
      product.setPrice(productDTO.getPrice());
      product.setMaker(makerOptional.get());
      productService.save(product);
      return ResponseEntity.ok().body(productService.toDTO(product));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable Long id) {
    if (id == null) {
      return ResponseEntity.badRequest().build();
    }
    Optional<Product> productOptional = productService.findById(id);
    if (productOptional.isPresent()) {
      productService.delete(productOptional.get());
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
