package com.example.blog.service;

import com.example.blog.entity.BlogEntity;
import com.example.blog.model.response.BlogDTO;

import java.util.List;

public interface BlogService {

    BlogEntity addNewBlog(BlogEntity blogEntity);

    List<BlogDTO> getAllBlog(int pageIndex, int pageSize, String search, Long author, Long category);

    BlogDTO getBlogById(Long id);

    Boolean deleteById(Long id);

}
