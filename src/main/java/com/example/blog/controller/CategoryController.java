package com.example.blog.controller;


import com.example.blog.entity.CategoryEntity;
import com.example.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("category")
public class CategoryController {

    CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> addNewCategory(@RequestBody CategoryEntity categoryEntity) {
        return ResponseEntity.ok(categoryService.addNewCategory(categoryEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteCategoryById(id));
    }

    @GetMapping()
    public ResponseEntity<List<CategoryEntity>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

}
