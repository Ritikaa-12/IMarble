package com.marble.service;

import com.marble.dto.ProductDto;
import java.util.List;

public interface ProductService {

    // Method to create a new product
    ProductDto createProduct(ProductDto productDto);

    // Method to get a single product by its ID
    ProductDto getProductById(Integer productId);

    // Method to get all products
    List<ProductDto> getAllProducts();

    // Method to update a product
    ProductDto updateProduct(Integer productId, ProductDto productDto);

    // Method to delete a product
    void deleteProduct(Integer productId);
}