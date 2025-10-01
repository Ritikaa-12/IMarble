package com.marble.service.impl;

import com.marble.dto.ProductDto;
import com.marble.entities.Product;
import com.marble.entities.SubCategory;
import com.marble.entities.Brand;
import com.marble.entities.Category;
import com.marble.repos.ProductRepository;
import com.marble.repos.SubCategoryRepository;
import com.marble.repos.BrandRepository;
import com.marble.repos.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

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

        if (product.getCategory() != null) {
            dto.setCategoryId(product.getCategory().getCategoryId());
            dto.setTitle(product.getCategory().getTitle());
        }

        return dto;
    }

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

        // 🔹 Set SubCategory
        if (dto.getSubCategoryId() != null) {
            subCategoryRepository.findById(dto.getSubCategoryId())
                    .ifPresent(product::setSubCategory);
        }

        // 🔹 Set Brand
        if (dto.getBrandId() != null) {
            brandRepository.findById(dto.getBrandId())
                    .ifPresent(product::setBrand);
        }

        // 🔹 Set Category (mandatory!)
        if (dto.getCategoryId() != null) {
            categoryRepository.findById(dto.getCategoryId())
                    .ifPresent(product::setCategory);
        } else {
            throw new RuntimeException("Category is required");
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
        return productRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList());
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

        if (productDto.getSubCategoryId() != null) {
            subCategoryRepository.findById(productDto.getSubCategoryId())
                    .ifPresent(product::setSubCategory);
        }

        if (productDto.getBrandId() != null) {
            brandRepository.findById(productDto.getBrandId())
                    .ifPresent(product::setBrand);
        }

        if (productDto.getCategoryId() != null) {
            categoryRepository.findById(productDto.getCategoryId())
                    .ifPresent(product::setCategory);
        } else {
            throw new RuntimeException("Category is required");
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
