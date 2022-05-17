package com.example.blog.service;

import com.example.blog.entity.BlogEntity;

import java.util.List;

public interface BlogService {

    BlogEntity addNewBlog(BlogEntity blogEntity);

    List<BlogEntity> getAllBlog(int pageIndex, int pageSize, String search);

    List<BlogEntity> getAllBlogByAuthorId(int pageIndex, int pageSize, Long id);

    List<BlogEntity> getAllBlogByCategory(int pageIndex, int pageSize, Long categoryId);
}
