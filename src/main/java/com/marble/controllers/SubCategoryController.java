package com.marble.controllers;

import com.marble.dto.SubCategoryDto;
import com.marble.service.SubCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @PostMapping
    public ResponseEntity<SubCategoryDto> createSubCategory(@RequestBody SubCategoryDto dto) {
        return new ResponseEntity<>(subCategoryService.createSubCategory(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubCategoryDto> getSubCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(subCategoryService.getSubCategoryById(id));
    }

    @GetMapping
    public ResponseEntity<List<SubCategoryDto>> getAllSubCategories() {
        return ResponseEntity.ok(subCategoryService.getAllSubCategories());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<SubCategoryDto>> getSubCategoriesByCategoryId(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(subCategoryService.getSubCategoriesByCategoryId(categoryId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategoryDto> updateSubCategory(@PathVariable Integer id, @RequestBody SubCategoryDto dto) {
        return ResponseEntity.ok(subCategoryService.updateSubCategory(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable Integer id) {
        subCategoryService.deleteSubCategory(id);
        return ResponseEntity.ok("SubCategory deleted successfully");
    }
}
