package com.application.rest.config;

import com.application.rest.entities.Maker;
import com.application.rest.entities.Product;
import com.application.rest.services.IMakerService;
import com.application.rest.services.IProductService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

  @Autowired
  private IMakerService makerService;

  @Autowired
  private IProductService productService;

  @Override
  public void run(String... args) throws Exception {
    Maker makerApple = Maker.builder().name("Apple").build();
    Maker makerSamsung = Maker.builder().name("Samsung").build();
    Maker makerHuawei = Maker.builder().name("Huawei").build();
    Maker makerXiaomi = Maker.builder().name("Xiaomi").build();
    Maker makerOppo = Maker.builder().name("Oppo").build();
    Maker makerVivo = Maker.builder().name("Vivo").build();
    Maker makerRealme = Maker.builder().name("Realme").build();
    Maker makerOnePlus = Maker.builder().name("OnePlus").build();
    Maker makerSony = Maker.builder().name("Sony").build();
    Maker makerLG = Maker.builder().name("LG").build();

    makerService.save(makerApple);
    makerService.save(makerSamsung);
    makerService.save(makerHuawei);
    makerService.save(makerXiaomi);
    makerService.save(makerOppo);
    makerService.save(makerVivo);
    makerService.save(makerRealme);
    makerService.save(makerOnePlus);
    makerService.save(makerSony);
    makerService.save(makerLG);

    Product product1 = new Product("iPhone 12", BigDecimal.valueOf(909), makerApple);
    Product product2 = new Product("Galaxy S21", BigDecimal.valueOf(849), makerSamsung);
    Product product3 = new Product("P40 Pro", BigDecimal.valueOf(799), makerHuawei);
    Product product4 = new Product("Mi 11", BigDecimal.valueOf(749), makerXiaomi);
    Product product5 = new Product("Find X3 Pro", BigDecimal.valueOf(1149), makerOppo);
    Product product6 = new Product("X60 Pro+", BigDecimal.valueOf(1199), makerVivo);
    Product product7 = new Product("X7 Pro", BigDecimal.valueOf(499), makerRealme);
    Product product8 = new Product("9 Pro", BigDecimal.valueOf(899), makerOnePlus);
    Product product9 = new Product("Xperia 1 II", BigDecimal.valueOf(1199), makerSony);
    Product product10 = new Product("Velvet", BigDecimal.valueOf(599), makerLG);

    productService.save(product1);
    productService.save(product2);
    productService.save(product3);
    productService.save(product4);
    productService.save(product5);
    productService.save(product6);
    productService.save(product7);
    productService.save(product8);
    productService.save(product9);
    productService.save(product10);
  }
}
