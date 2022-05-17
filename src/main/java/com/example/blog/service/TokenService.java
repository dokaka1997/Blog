package com.example.blog.service;

import com.example.blog.model.request.JwtRequest;
import com.example.blog.model.response.JwtResponse;


public interface TokenService {
    JwtResponse generateToken(JwtRequest authenticationRequest) throws Exception;
}
