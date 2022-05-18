package com.example.blog.controller;

import com.example.blog.entity.BlogEntity;
import com.example.blog.model.response.BlogDTO;
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
    public ResponseEntity<List<BlogDTO>> getAllBlog(@RequestParam int pageIndex, @RequestParam int pageSize,
                                                       @RequestParam(required = false, defaultValue = "") String search,
                                                       @RequestParam(required = false) Long author,
                                                       @RequestParam(required = false) Long category) {
        return ResponseEntity.ok(blogService.getAllBlog(pageIndex, pageSize, search, author, category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogDTO> getBlogById(@PathVariable Long id) {
        return ResponseEntity.ok(blogService.getBlogById(id));
    }

}
