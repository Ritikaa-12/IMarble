package com.marble.controllers;

import com.marble.dto.ProductDto;
import com.marble.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Create
    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    // Read by ID
    @GetMapping("/getById/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    // Read all
    @GetMapping("/getAll")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    // Update
    @PutMapping("/update/{id}")
    public ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "Product with ID " + id + " deleted successfully.";
    }
}
