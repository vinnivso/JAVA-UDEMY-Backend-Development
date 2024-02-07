package com.vinnivso.monolythicspring.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnivso.monolythicspring.services.ProductService;
import com.vinnivso.monolythicspring.shared.ProductDTO;
import com.vinnivso.monolythicspring.view.model.ProductRequest;
import com.vinnivso.monolythicspring.view.model.ProductResponse;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  // ResponseEntity é uma classe no JAVA que sabe trabalhar com respotas no
  // SPRING.
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    List<ProductDTO> products = productService.getAllProducts();
    ModelMapper mapper = new ModelMapper();
    List<ProductResponse> response = products.stream()
        .map(productDTO -> mapper.map(productDTO, ProductResponse.class))
        .collect(Collectors.toList());

    // Returning a List of products and the status code.
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<ProductResponse>> getProductById(@PathVariable Integer id) {
    // I'm used the Exception, but if not ... I can use trycatch syntax and send
    // NO_CONTENT in ResponseEntity.
    // try {
    Optional<ProductDTO> productDTO = productService.getProductById(id);
    ProductResponse product = new ModelMapper().map(productDTO.get(), ProductResponse.class);
    return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);
    // } catch (Exception e) {
    // return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    // }
  }

  // Se as propriedades informadas no Body da requisição estiverem de acordo com a
  // Model (Mesmo norme), você pode simplesmente usar o @RequestBody. Pois será
  // realizado um binding por parte do Spring.
  @PostMapping
  public ResponseEntity<ProductResponse> newProduct(@RequestBody ProductRequest product) {
    ModelMapper mapper = new ModelMapper();
    ProductDTO productDTO = mapper.map(product, ProductDTO.class);
    productService.newProduct(productDTO);
    return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
    productService.deleteProductById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest product) {
    ModelMapper mapper = new ModelMapper();
    ProductDTO productDTO = mapper.map(product, ProductDTO.class);
    productService.updateProduct(id, productDTO);

    return new ResponseEntity<>(mapper.map(productDTO, ProductResponse.class), HttpStatus.OK);
  }
}