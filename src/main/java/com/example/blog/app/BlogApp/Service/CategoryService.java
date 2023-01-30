package com.example.blog.app.BlogApp.Service;

import com.example.blog.app.BlogApp.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
     CategoryDto createCategory(CategoryDto categoryDto);
     CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId );
     CategoryDto getCategory(Integer categoryId);
     List<CategoryDto>getAllCategories();
     void deleteCategory(Integer categoryId);

}
