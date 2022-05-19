package com.example.blog.model.response;

import lombok.Data;

import java.sql.Date;

@Data
public class BlogDTO {
    private Long id;

    private Date createDate;

    private Date updatedDate;

    private Long author;

    private String authorName;

    private String content;

    private String title;

    private Long category;

    private String categoryName;

    private Long imageId;
}
