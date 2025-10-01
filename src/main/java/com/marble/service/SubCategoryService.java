package com.marble.service;

import java.util.List;

import com.marble.dto.SubCategoryDto;

public interface SubCategoryService {
	SubCategoryDto createSubCategory(SubCategoryDto SubCategoryDto);
	void deleteSubCategory(Integer SubCategoryId);
	SubCategoryDto getSubCategoryById(Integer SubCategoryId);
	List<SubCategoryDto> getAllSubCategories();
	SubCategoryDto updateSubCategory(Integer SubCategoryId,SubCategoryDto SubCategoryDto);
	List<SubCategoryDto> getSubCategoriesByCategoryId(Integer categoryId);
}
