package com.marble.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="subcategory_id")
	private Integer subCategoryId;
	

    // Many SubCategories belong to One Category
	 @ManyToOne
	 @JoinColumn(name = "category_id", nullable = false)  // foreign key
	 private Category category;
	
	@Column(name="title", nullable =false)
	private String title;
	
	@Column(name="description", nullable =false)
	private String description;
	
	@Column(name="status", nullable =false)
	private Boolean status;
	
	// ask ++++++++++++++++++++++++++++(can store url of image using string datatype)
	@Column(name="images", nullable =false)
	private String images;
	
}
