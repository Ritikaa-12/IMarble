package com.marble.service.impl;

import com.marble.dto.ProductDto;
import com.marble.entities.Product;
import com.marble.entities.SubCategory;
import com.marble.entities.Brand;
import com.marble.repos.ProductRepository;
import com.marble.repos.SubCategoryRepository;
import com.marble.repos.BrandRepository;
import com.marble.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private BrandRepository brandRepository;

    // Convert Entity -> DTO
    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductId(product.getProductId());
        dto.setTitle(product.getTitle());
        dto.setDescription(product.getDescription());
        dto.setPricePerUnit(product.getPricePerUnit());
        dto.setUnit(product.getUnit());
        dto.setModelNo(product.getModelNo());
        dto.setImage(product.getImage());
        dto.setMinStockLevel(product.getMinStockLevel());

        if (product.getSubCategory() != null) {
            dto.setSubCategoryId(product.getSubCategory().getSubCategoryId());
            dto.setSubCategoryTitle(product.getSubCategory().getTitle());
        }

        if (product.getBrand() != null) {
            dto.setBrandId(product.getBrand().getBrandId());
            dto.setBrandTitle(product.getBrand().getTitle());
        }

        return dto;
    }

    // Convert DTO -> Entity
    private Product mapToEntity(ProductDto dto) {
        Product product = new Product();
        product.setProductId(dto.getProductId());
        product.setTitle(dto.getTitle());
        product.setDescription(dto.getDescription());
        product.setPricePerUnit(dto.getPricePerUnit());
        product.setUnit(dto.getUnit());
        product.setModelNo(dto.getModelNo());
        product.setImage(dto.getImage());
        product.setMinStockLevel(dto.getMinStockLevel());

        // Ensure SubCategory exists
        if (dto.getSubCategoryId() == null) {
            throw new RuntimeException("SubCategoryId is required.");
        }
        SubCategory subCategory = subCategoryRepository.findById(dto.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found with ID: " + dto.getSubCategoryId()));
        product.setSubCategory(subCategory);

        // Set Brand if present
        if (dto.getBrandId() != null) {
            Brand brand = brandRepository.findById(dto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + dto.getBrandId()));
            product.setBrand(brand);
        }

        return product;
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product saved = productRepository.save(product);
        return mapToDto(saved);
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
        return mapToDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));

        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setPricePerUnit(productDto.getPricePerUnit());
        product.setUnit(productDto.getUnit());
        product.setModelNo(productDto.getModelNo());
        product.setImage(productDto.getImage());
        product.setMinStockLevel(productDto.getMinStockLevel());

        // Ensure SubCategory exists
        if (productDto.getSubCategoryId() == null) {
            throw new RuntimeException("SubCategoryId is required.");
        }
        SubCategory subCategory = subCategoryRepository.findById(productDto.getSubCategoryId())
                .orElseThrow(() -> new RuntimeException("SubCategory not found with ID: " + productDto.getSubCategoryId()));
        product.setSubCategory(subCategory);

        // Set Brand if present
        if (productDto.getBrandId() != null) {
            Brand brand = brandRepository.findById(productDto.getBrandId())
                    .orElseThrow(() -> new RuntimeException("Brand not found with ID: " + productDto.getBrandId()));
            product.setBrand(brand);
        }

        Product updated = productRepository.save(product);
        return mapToDto(updated);
    }

    @Override
    public void deleteProduct(Integer productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
        productRepository.deleteById(productId);
    }
}
