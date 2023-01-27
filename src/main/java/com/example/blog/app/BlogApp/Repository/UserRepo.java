package com.example.blog.app.BlogApp.Repository;

import com.example.blog.app.BlogApp.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
