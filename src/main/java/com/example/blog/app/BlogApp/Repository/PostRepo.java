package com.example.blog.app.BlogApp.Repository;

import com.example.blog.app.BlogApp.Entities.Category;
import com.example.blog.app.BlogApp.Entities.Post;
import com.example.blog.app.BlogApp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

}
