package com.example.blog.app.BlogApp.Controller;

import com.example.blog.app.BlogApp.Payloads.ApiResponse;
import com.example.blog.app.BlogApp.Payloads.CategoryDto;
import com.example.blog.app.BlogApp.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Validated
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add/category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody  CategoryDto categoryDto){
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);

    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto,
                                                     @PathVariable(value = "categoryId")Integer categoryId) {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(updatedCategory);

    }
    @GetMapping("/list")
    public ResponseEntity<List<CategoryDto>>getAllCategories(){
        List<CategoryDto> allCategoriesList = this.categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(allCategoriesList);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto>getCategoryById(@PathVariable
                                                              (value = "categoryId")Integer categoryId){
        CategoryDto category = this.categoryService.getCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable(value = "categoryId")Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity(new ApiResponse("Category delete successfully",true),HttpStatus.OK);
    }
}
