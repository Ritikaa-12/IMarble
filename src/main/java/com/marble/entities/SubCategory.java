package com.marble.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
	

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SubCategory {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY) // only ONE auto_increment
	    @Column(name = "subcategory_id")
	    private Integer subCategoryId;

    @Column(nullable = false)
    private String title;

    private String description;

    private String images; // URL to an image for the sub-category

    private Boolean status = true;

    // Many SubCategories belong to One Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

 
}