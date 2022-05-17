package com.example.blog.service;


import com.example.blog.entity.CategoryEntity;

import java.util.List;


public interface CategoryService {

    CategoryEntity addNewCategory(CategoryEntity categoryEntity);

    Boolean deleteCategoryById(Long id);

    List<CategoryEntity> getAllCategory();
}
