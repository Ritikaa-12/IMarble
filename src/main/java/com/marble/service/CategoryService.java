package com.marble.service;

import java.util.List;

import com.marble.dto.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);
	void deleteCategory(Integer categoryId);
	CategoryDto getCategoryById(Integer categoryId);
	List<CategoryDto> getAllCategories();
	CategoryDto updateCategory(Integer categoryId,CategoryDto categoryDto);
}
