package com.vinnivso.monolythicspring.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.vinnivso.monolythicspring.model.Product;
import com.vinnivso.monolythicspring.model.exception.ResourceNotFoundException;

@Repository
public class ProductRepository {

  private ArrayList<Product> products = new ArrayList<Product>();
  private Integer lastId = 0;

  // Posso utilizar o LIST, ele consegue trabalhar com qualquer tipo de List,
  // ArrayList, dentre outros.
  /**
   * Method to return all products.
   *
   * @return Get a list of all products.
   */
  public List<Product> getAllProducts() {
    return products;
  }

  /**
   * Method to get a product by Id.
   *
   * @param id ID from product to be get.
   * @return Get Product By Id.
   */
  public Optional<Product> getProductById(Integer id) {
    return products
        .stream()
        .filter(element -> element.getId() == id)
        .findFirst();
  }

  /**
   * Method to create a new product.
   *
   * @param product Product to be inserted in the list as new product.
   * @return
   */
  public Product newProduct(Product product) {
    lastId++;
    product.setId(lastId);
    products.add(product);
    return product;
  }

  /**
   * Method to delete a product by Id.
   *
   * @param id Id from product to be deleted.
   */
  public void deleteProductById(Integer id) {
    products.removeIf(element -> element.getId() == id);
  }

  /**
   * Method to update a product by Id.
   *
   * @param product Product updated.
   * @param id Id from product to be updated.
   * @return Product updated maintaining the same Id.
   */
  public Product updateProduct(Product product) {
    // Look for the the Product.
    Optional<Product> productFound = getProductById(product.getId());
    if (productFound.isEmpty()) {
      throw new ResourceNotFoundException("Product not found.");
    }

    // Remove the oldest Product.
    deleteProductById(product.getId());

    // Adding the new Product updated.
    products.add(product);

    // Returning the Product updated.
    return product;
  }
}
