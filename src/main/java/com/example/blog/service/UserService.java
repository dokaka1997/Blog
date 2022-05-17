package com.example.blog.service;


import com.example.blog.entity.UserEntity;
import com.example.blog.model.request.CreateUserRequest;
import com.example.blog.model.request.LoginUserRequest;

public interface UserService {
    boolean createUser(CreateUserRequest userRequest);

    UserEntity login(LoginUserRequest loginUserRequest);
}
