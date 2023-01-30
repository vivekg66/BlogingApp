package com.example.blog.app.BlogApp.Repository;

import com.example.blog.app.BlogApp.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Integer> {
}
