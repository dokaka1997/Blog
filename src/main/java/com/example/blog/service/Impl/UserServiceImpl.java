package com.example.blog.service.Impl;


import com.example.blog.entity.UserEntity;
import com.example.blog.model.request.ChangePassRequest;
import com.example.blog.model.request.CreateUserRequest;
import com.example.blog.model.request.LoginUserRequest;
import com.example.blog.model.request.UpdateUserRequest;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;


    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean createUser(CreateUserRequest userRequest) {
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = mapper.map(userRequest, UserEntity.class);
        userEntity.setRole(0L);
        if (userRepository.getByUsername(userRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Username existed");
        }
        try {
            userRepository.save(userEntity);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public UserEntity login(LoginUserRequest loginUserRequest) {
        Optional<UserEntity> userEntity = userRepository.getByUsernameAndPassword(loginUserRequest.getUsername(), loginUserRequest.getPassword());
        return userEntity.orElse(null);
    }

    @Override
    public UserEntity updateUser(UpdateUserRequest updateUserRequest) {
        Optional<UserEntity> optionalUser = userRepository.findById(updateUserRequest.getId());
        UserEntity userEntity = new UserEntity();
        if (optionalUser.isPresent()) {
            userEntity = optionalUser.get();
            userEntity.setEmail(updateUserRequest.getEmail());
            userEntity.setDob(updateUserRequest.getDob());
            userEntity.setPhone(updateUserRequest.getPhone());
            userEntity.setName(updateUserRequest.getName());
            userRepository.save(userEntity);
        }
        return userEntity;
    }

    @Override
    public UserEntity changePass(ChangePassRequest changePassRequest) {
        Optional<UserEntity> optionalUser = userRepository.findById(changePassRequest.getId());
        UserEntity userEntity = new UserEntity();
        if (optionalUser.isPresent()) {
            userEntity = optionalUser.get();
            if (!changePassRequest.getOldPassword().equals(userEntity.getPassword())) {
                throw new RuntimeException("Old password incorrect!!!");
            }
            userEntity.setPassword(changePassRequest.getNewPassword());
            userRepository.save(userEntity);
        }
        return userEntity;
    }
}
