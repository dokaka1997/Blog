package com.example.blog.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UpdateUserRequest {
    private Long id;
    private String name;
    private Date dob;
    private String email;
    private String phone;
}
