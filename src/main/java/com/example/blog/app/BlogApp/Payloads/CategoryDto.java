package com.example.blog.app.BlogApp.Payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @Size(min = 6,message = "Category Title cannot be less then 6 characters")
    private String categoryTitle;
    @NotBlank
    @Size(min = 6,message = "Category description cannot be less then 6 characters")
    private String categoryDescription;
}
