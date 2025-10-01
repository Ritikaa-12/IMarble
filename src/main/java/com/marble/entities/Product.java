package com.marble.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT") // For longer descriptions
    private String description;
    
    // A Product now belongs to ONLY ONE SubCategory
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id")
    private SubCategory subCategory;
    
    // A Product now belongs to ONLY ONE Brand (Simpler & more common)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    
    @Column(nullable = false)
    private Double pricePerUnit;

    private String unit; // e.g., "sq. ft.", "piece"

    private String modelNo; // (HSN code)

    private String image; // URL for the main product image

    private Integer minStockLevel;
}