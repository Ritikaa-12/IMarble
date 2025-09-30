// src/main/java/com/marble/service/impl/ProductServiceImpl.java
package com.marble.service.impl;

import com.marble.dto.ProductDto;
import com.marble.entities.Brand;
import com.marble.entities.Product;
import com.marble.entities.SubCategory;
import com.marble.repos.BrandRepository;
import com.marble.repos.ProductRepsitory;
import com.marble.repos.SubCategoryRepository;
import com.marble.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // Marks this class as a Spring service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepsitory productRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private BrandRepository brandRepository;

  
}