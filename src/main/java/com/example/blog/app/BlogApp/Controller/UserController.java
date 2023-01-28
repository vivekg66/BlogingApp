package com.example.blog.app.BlogApp.Controller;

import com.example.blog.app.BlogApp.Payloads.ApiResponse;
import com.example.blog.app.BlogApp.Payloads.UserDto;
import com.example.blog.app.BlogApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = this.userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDto>updateUserDetails
            (@Valid @RequestBody UserDto userDto, @PathVariable(value = "userId")int userId){
       UserDto updateUser = this.userService.updateUser(userDto,userId);
       return ResponseEntity.status(HttpStatus.OK).body(updateUser);

    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable(value = "userId")int userId){
        this.userService.deleteUser(userId);
        return new ResponseEntity(new ApiResponse("user deleted ",true),HttpStatus.OK);

    }

    @GetMapping("/list")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        List<UserDto> userList = this.userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<UserDto>getUserById(@PathVariable (value = "userId")int userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }



}
