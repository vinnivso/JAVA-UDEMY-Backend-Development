package com.vinnivso.monolythicspring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnivso.monolythicspring.model.Product;
import com.vinnivso.monolythicspring.repository.ProductRepository;

@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  /**
   * Method to return all products.
   *
   * @return Get a list of all products.
   */
  public List<Product> getAllProducts() {
    return productRepository.getAllProducts();
  }

  /**
   * Method to get a product by Id.
   *
   * @param id ID from product to be get.
   * @return Get Product By Id.
   */
  public Optional<Product> getProductById(Integer id) {
    return productRepository.getProductById(id);
  }

  /**
   * Method to create a new product.
   *
   * @param product Product to be inserted in the list as new product.
   * @return
   */
  public Product newProduct(Product product) {
    return productRepository.newProduct(product);
  }

  /**
   * Method to delete a product by Id.
   *
   * @param id Id from product to be deleted.
   */
  public void deleteProductById(Integer id) {
    productRepository.deleteProductById(id);
  }

  /**
   * Method to update a product by Id.
   *
   * @param product Product updated.
   * @param id Id from product to be updated.
   * @return Product updated maintaining the same Id.
   */
  public Product updateProduct(Integer id, Product product) {
    product.setId(id);
    return productRepository.updateProduct(product);
  }
}
