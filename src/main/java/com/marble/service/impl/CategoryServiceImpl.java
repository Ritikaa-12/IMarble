package com.marble.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.marble.dto.CategoryDto;
import com.marble.entities.Category;
import com.marble.repos.CategoryRepsitory;
import com.marble.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepsitory categoryRepository;

	// helper method hai entitiy ko dto me karne ke liye
	private CategoryDto entityToDto(Category category) {
		CategoryDto dto = new CategoryDto();
		dto.setCategoryId(category.getCategoryId());
		dto.setCategoryId(category.getCategoryId());

		dto.setTitle(category.getTitle());

		dto.setDescription(category.getDescription());

		dto.setImages(category.getImages());

		dto.setStatus(category.getStatus());
		return dto;
	}

	// dto------------>Entity
	private Category dtoToEntity(CategoryDto dto) {

		Category category = new Category();

		category.setTitle(dto.getTitle());

		category.setDescription(dto.getDescription());

		category.setImages(dto.getImages());

		category.setStatus(dto.getStatus());

		return category;
	}

	public CategoryServiceImpl(CategoryRepsitory categoryRepsitory) {
		this.categoryRepository = categoryRepsitory;
	}

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.dtoToEntity(categoryDto);
		Category savedCategory = categoryRepository.save(category);
		return this.entityToDto(savedCategory);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category Not Found By ID: " + categoryId));
		categoryRepository.delete(category);

	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category Not OFund By ID: " + categoryId));

		return this.entityToDto(category);
	}

	// samjhadena
	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();

		return categories.stream().map(this::entityToDto).collect(Collectors.toList());
	}

	@Override
	public CategoryDto updateCategory(Integer categoryId, CategoryDto categoryDto) {
		// TODO Auto-generated method stub
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not found with Id: " + categoryId));

		category.setTitle(categoryDto.getTitle());

		category.setDescription(categoryDto.getDescription());

		category.setImages(categoryDto.getImages());

		category.setStatus(categoryDto.getStatus());

		Category updatedCategory = categoryRepository.save(category);

		return this.entityToDto(updatedCategory);

	}

}
