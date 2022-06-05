package com.example.blog.model.request;

import lombok.Data;
@Data
public class ChangePassRequest {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
