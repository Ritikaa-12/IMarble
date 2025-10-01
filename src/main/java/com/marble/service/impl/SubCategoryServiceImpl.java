
package com.marble.service.impl;

import com.marble.dto.SubCategoryDto;
import com.marble.entities.Category;
import com.marble.entities.SubCategory;
import com.marble.repos.CategoryRepsitory;
import com.marble.repos.SubCategoryRepository;
import com.marble.service.SubCategoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepsitory categoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepsitory categoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public SubCategoryDto createSubCategory(SubCategoryDto subCategoryDto) {
        
        Category category = categoryRepository.findById(subCategoryDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Parent Category not found"));

        SubCategory subCategory = this.dtoToEntity(subCategoryDto);
        subCategory.setCategory(category); 

        SubCategory savedSubCategory = subCategoryRepository.save(subCategory);
        return this.entityToDto(savedSubCategory);
    }

    @Override
    public SubCategoryDto getSubCategoryById(Integer subCategoryId) {
        SubCategory subCategory = subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));
        return this.entityToDto(subCategory);
    }

    // samjha dena
    @Override
    public List<SubCategoryDto> getAllSubCategories() {
        return subCategoryRepository.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    @Override
    public List<SubCategoryDto> getSubCategoriesByCategoryId(Integer categoryId) {
        return subCategoryRepository.findAll().stream()
                .filter(sub -> sub.getCategory().getCategoryId().equals(categoryId))
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSubCategory(Integer subCategoryId) {
        subCategoryRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));
        subCategoryRepository.deleteById(subCategoryId);
    }


    private SubCategoryDto entityToDto(SubCategory subCategory) {
        SubCategoryDto dto = new SubCategoryDto();
        dto.setSubCategoryId(subCategory.getSubCategoryId());
        dto.setTitle(subCategory.getTitle());
        dto.setDescription(subCategory.getDescription());
        dto.setImages(subCategory.getImages());
        dto.setStatus(subCategory.getStatus());
        dto.setCategoryId(subCategory.getCategory().getCategoryId());
        return dto;
    }

    private SubCategory dtoToEntity(SubCategoryDto dto) {
        SubCategory subCategory = new SubCategory();
        subCategory.setTitle(dto.getTitle());
        subCategory.setDescription(dto.getDescription());
        subCategory.setImages(dto.getImages());
        subCategory.setStatus(dto.getStatus());
        return subCategory;
    }

    // nahi banra yrr upadte
	@Override
	public SubCategoryDto updateSubCategory(Integer SubCategoryId, SubCategoryDto SubCategoryDto) {
		// TODO Auto-generated method stub
		return null;
	}
}