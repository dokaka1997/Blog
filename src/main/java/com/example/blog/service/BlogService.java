package com.example.blog.service;

import com.example.blog.entity.BlogEntity;

import java.util.List;

public interface BlogService {

    BlogEntity addNewBlog(BlogEntity blogEntity);

    List<BlogEntity> getAllBlog(int pageIndex, int pageSize, String search, Long author, Long category);

}
