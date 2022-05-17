package com.example.blog.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Table(name = "blog")
public class BlogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createDate;

    private Date updatedDate;

    private Long author;

    private String content;

    private String title;

    private Long category;
}
