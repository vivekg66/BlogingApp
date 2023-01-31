package com.example.blog.app.BlogApp.Service;

import com.example.blog.app.BlogApp.Entities.Post;
import com.example.blog.app.BlogApp.Payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);

    PostDto getPostById(Integer postId);

    List<PostDto> getAllPost();

    void deletePostById(Integer postId);

    List<PostDto>getByUser(Integer userId);

    List<PostDto>getByCategory(Integer categoryId);

}
