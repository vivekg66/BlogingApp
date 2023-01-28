package com.example.blog.app.BlogApp.Payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min = 3,message = "Username must contain atleast 3 characters!!!")
    private String name;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,message = "Email address is not valid!!!")
    private String email;
    @NotEmpty
    @Size(min = 5,max = 16,message = "Password must have min of 5 characters!!!")
    private String password;
    @NotNull
    private String about;
}
