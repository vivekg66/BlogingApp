package com.example.blog.app.BlogApp.Controller;

import com.example.blog.app.BlogApp.Entities.Post;
import com.example.blog.app.BlogApp.Payloads.ApiResponse;
import com.example.blog.app.BlogApp.Payloads.PostDto;
import com.example.blog.app.BlogApp.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable(value = "userId")Integer userId,
                                              @PathVariable(value = "categoryId")Integer categoryId) {
        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>>getPostByUserId(@PathVariable(value = "userId") Integer userId){
        List<PostDto> postsByUser = this.postService.getByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(postsByUser);

    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>>getPostByCategoryId(@PathVariable(value = "categoryId") Integer categoryId){
        List<PostDto> postsByCategory = this.postService.getByCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(postsByCategory);

    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(value = "postId")Integer postId){
        PostDto postById = this.postService.getPostById(postId);
        return ResponseEntity.status(HttpStatus.OK).body(postById);
    }

    @GetMapping("all")
    public ResponseEntity<List<PostDto>>getAllPost(){
        List<PostDto> allPost = this.postService.getAllPost();
        return ResponseEntity.status(HttpStatus.OK).body(allPost);
    }

    @DeleteMapping("delete/{postId}")
    public ResponseEntity<ApiResponse>deletePost(@PathVariable (value = "postId")Integer postId) {
        this.postService.deletePostById(postId);
        return new ResponseEntity(new ApiResponse("post deleted successfully", true), HttpStatus.OK);

    }

    @PutMapping("update/{postId}")
    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,
                                             @PathVariable(value = "postId")Integer postId){
        PostDto updatedPostDto = this.postService.updatePost(postDto, postId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPostDto);
    }
}
