package com.example.blog.app.BlogApp.Service.ServiceImpl;

import com.example.blog.app.BlogApp.Entities.Category;
import com.example.blog.app.BlogApp.Exceptions.ResourceNotFoundException;
import com.example.blog.app.BlogApp.Payloads.CategoryDto;
import com.example.blog.app.BlogApp.Repository.CategoryRepo;
import com.example.blog.app.BlogApp.Service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category categoryAdded = this.categoryRepo.save(category);
        return this.modelMapper.map(categoryAdded, CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        category.setCategoryTitle(category.getCategoryTitle());
        category.setCategoryDescription(category.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory, CategoryDto.class);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        return this.modelMapper.map(category, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categoryList = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categoryList.stream().map(category -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        this.categoryRepo.delete(category);

    }
}
