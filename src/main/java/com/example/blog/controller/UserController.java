package com.example.blog.controller;


import com.example.blog.entity.UserEntity;
import com.example.blog.model.request.ChangePassRequest;
import com.example.blog.model.request.CreateUserRequest;
import com.example.blog.model.request.JwtRequest;
import com.example.blog.model.request.UpdateUserRequest;
import com.example.blog.service.TokenService;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {

    UserService userService;
    private TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }


    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(tokenService.generateToken(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> createUser(@RequestBody CreateUserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<UserEntity> createUser(@RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(updateUserRequest));
    }

    @PutMapping("/change_pass")
    public ResponseEntity<UserEntity> changePass(@RequestBody ChangePassRequest changePassRequest) {
        return ResponseEntity.ok(userService.changePass(changePassRequest));
    }

}
