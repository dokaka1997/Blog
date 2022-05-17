package com.example.blog.model.response;

import com.example.blog.entity.UserEntity;
import lombok.Data;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    UserEntity user;

    public JwtResponse(String jwtToken, UserEntity user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }
}