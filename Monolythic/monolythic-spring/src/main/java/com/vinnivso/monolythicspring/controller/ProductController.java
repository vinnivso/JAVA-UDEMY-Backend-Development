package com.vinnivso.monolythicspring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vinnivso.monolythicspring.model.Product;
import com.vinnivso.monolythicspring.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<Product> getAllProducts() {
    return productService.getAllProducts();
  }

  @GetMapping("/{id}")
  public Optional<Product> getProductById(@PathVariable Integer id) {
    return productService.getProductById(id);
  }

  // Se as propriedades informadas no Body da requisição estiverem de acordo com a
  // Model (Mesmo norme), você pode simplesmente usar o @RequestBody. Pois será
  // realizado um binding por parte do Spring.
  @PostMapping
  public Product newProduct(@RequestBody Product product) {
    return productService.newProduct(product);
  }

  @DeleteMapping("/{id}")
  public String deleteProductById(@PathVariable Integer id) {
    productService.deleteProductById(id);
    return "Product with ID: " + id + "has been deleted successfully!";
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable Integer id, @RequestBody Product product) {
    return productService.updateProduct(id, product);
  }
}
