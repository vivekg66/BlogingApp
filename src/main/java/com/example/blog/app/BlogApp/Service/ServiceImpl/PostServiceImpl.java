package com.example.blog.app.BlogApp.Service.ServiceImpl;

import com.example.blog.app.BlogApp.Entities.Category;
import com.example.blog.app.BlogApp.Entities.Post;
import com.example.blog.app.BlogApp.Entities.User;
import com.example.blog.app.BlogApp.Exceptions.ResourceNotFoundException;
import com.example.blog.app.BlogApp.Payloads.PostDto;
import com.example.blog.app.BlogApp.Repository.CategoryRepo;
import com.example.blog.app.BlogApp.Repository.PostRepo;
import com.example.blog.app.BlogApp.Repository.UserRepo;
import com.example.blog.app.BlogApp.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));

        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost = this.postRepo.save(post);

       return this.modelMapper.map(newPost,PostDto.class);

    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post savedPost = this.postRepo.save(post);
        PostDto updatedPost = this.modelMapper.map(savedPost, PostDto.class);
        return updatedPost;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> postList = this.postRepo.findAll();
        List<PostDto> postDtoList = postList.stream().map((post)
                -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public void deletePostById(Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
        this.postRepo.delete(post);

    }

    @Override
    public List<PostDto> getByUser(Integer userId) {
        User user = this.userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userId", userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        return postDtos;

    }

    @Override
    public List<PostDto> getByCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
        List<Post> postByCategory = this.postRepo.findByCategory(category);
        List<PostDto> postDtoList = postByCategory.stream().map((post) ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }
}
