package com.example.blog.service;


import com.example.blog.entity.UserEntity;
import com.example.blog.model.request.ChangePassRequest;
import com.example.blog.model.request.CreateUserRequest;
import com.example.blog.model.request.LoginUserRequest;
import com.example.blog.model.request.UpdateUserRequest;

public interface UserService {
    boolean createUser(CreateUserRequest userRequest);

    UserEntity login(LoginUserRequest loginUserRequest);

    UserEntity updateUser(UpdateUserRequest updateUserRequest);

    UserEntity changePass(ChangePassRequest changePassRequest);
}
