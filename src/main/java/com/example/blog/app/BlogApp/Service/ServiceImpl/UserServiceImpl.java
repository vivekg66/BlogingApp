package com.example.blog.app.BlogApp.Service.ServiceImpl;

import com.example.blog.app.BlogApp.Entities.User;
import com.example.blog.app.BlogApp.Exceptions.ResourceNotFoundException;
import com.example.blog.app.BlogApp.Payloads.UserDto;
import com.example.blog.app.BlogApp.Repository.UserRepo;
import com.example.blog.app.BlogApp.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public User DtoToUser(UserDto userDto) {
        return this.modelMapper.map(userDto,User.class);
    }


    public UserDto UserToDto(User user) {
        return this.modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.DtoToUser(userDto);
        User savedUser = this.userRepo.save(user);
        return this.UserToDto(savedUser) ;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        User updateUser = this.userRepo.save(user);
        return this.UserToDto(updateUser);
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        return this.UserToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = this.userRepo.findAll();
        List<UserDto> userDtoList = userList.stream().map(this::UserToDto).collect(Collectors.toList());
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);

    }
}
