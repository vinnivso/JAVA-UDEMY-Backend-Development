package com.vinnivso.monolythicspring.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vinnivso.monolythicspring.model.Product;
import com.vinnivso.monolythicspring.model.exception.ResourceNotFoundException;
import com.vinnivso.monolythicspring.repository.ProductRepository;
import com.vinnivso.monolythicspring.shared.ProductDTO;


@Service
public class ProductService {
  @Autowired
  private ProductRepository productRepository;

  /**
   * Method to return all products.
   *
   * @return Get a list of all products.
   */
  public List<ProductDTO> getAllProducts() {
    // The Repository will return a product model, so we are building in a way to
    // convert Product model from repository to ProductDTO.
    List<Product> products = productRepository.findAll();
    return products.stream()
        .map(product -> new ModelMapper().map(product, ProductDTO.class))
        .collect(Collectors.toList());
  }

  /**
   * Method to get a product by Id.
   *
   * @param id ID from product to be get.
   * @return Get Product By Id.
   */
  public Optional<ProductDTO> getProductById(Integer id) {
    Optional<Product> product = productRepository.findById(id);

    // If not found a product with the mentioned ID, throw an exception.
    if (product.isEmpty()) {
      throw new ResourceNotFoundException("ID not found");
    }

    // Use ".get()" to return values from Optional.
    ProductDTO productDTO = new ModelMapper().map(product.get(), ProductDTO.class);

    // Creating an Optional to return, based on Product returned.
    return Optional.of(productDTO);
  }

  /**
   * Method to create a new product.
   *
   * @param product Product to be inserted in the list as new product.
   * @return
   */
  public ProductDTO newProduct(ProductDTO productDTO) {
    // Creating an Object mapping.
    ModelMapper mapper = new ModelMapper();

    // Converting ProductDTO to Product.
    Product product = mapper.map(productDTO, Product.class);

    // Saving the Product on DB.
    productRepository.save(product);
    productDTO.setId(product.getId());

    // Returning the ProductDTO updated.
    return productDTO;
  }

  /**
   * Method to delete a product by Id.
   *
   * @param id Id from product to be deleted.
   */
  public void deleteProductById(Integer id) {
    // Verify if the product exists on DB.
    Optional<Product> product = productRepository.findById(id);

    // If not found a product with the mentioned ID, throw an exception.
    if (product.isEmpty()) {
      throw new ResourceNotFoundException("ID not found");
    }

    // Remove the Product on DB.
    productRepository.deleteById(id);
  }

  /**
   * Method to update a product by Id.
   *
   * @param product Product updated.
   * @param id      Id from product to be updated.
   * @return Product updated maintaining the same Id.
   */
  public ProductDTO updateProduct(Integer id, ProductDTO productDTO) {
    // Pass the ID to ProductDTO.
    productDTO.setId(id);

    // Create an Object mapping.
    ModelMapper mapper = new ModelMapper();

    // Convert the ProductDTO to Product.
    Product product = mapper.map(productDTO, Product.class);

    // Update the Product on DB.
    productRepository.save(product);

    // Return the ProductDTO updated.
    return productDTO;
  }
}
