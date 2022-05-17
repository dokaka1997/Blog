package com.example.blog.controller;

import com.example.blog.entity.BlogEntity;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("blog")
public class BlogController {

    BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping
    public ResponseEntity<BlogEntity> addNewBlog(@RequestBody BlogEntity blogEntity) {
        return ResponseEntity.ok(blogService.addNewBlog(blogEntity));
    }

    @GetMapping
    public ResponseEntity<List<BlogEntity>> getAllBlog(@RequestParam int pageIndex, @RequestParam int pageSize, @RequestParam String search) {
        return ResponseEntity.ok(blogService.getAllBlog(pageIndex, pageSize, search));
    }

    @GetMapping("/author")
    public ResponseEntity<List<BlogEntity>> getAllBlogByAuthor(@RequestParam int pageIndex, @RequestParam int pageSize, @RequestParam Long author) {
        return ResponseEntity.ok(blogService.getAllBlogByAuthorId(pageIndex, pageSize, author));
    }

    @GetMapping("/category")
    public ResponseEntity<List<BlogEntity>> getAllBlogByCategory(@RequestParam int pageIndex, @RequestParam int pageSize, @RequestParam Long category) {
        return ResponseEntity.ok(blogService.getAllBlogByCategory(pageIndex, pageSize, category));
    }

}
