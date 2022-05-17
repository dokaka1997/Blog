package com.example.blog.service.Impl;

import com.example.blog.entity.BlogEntity;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public BlogEntity addNewBlog(BlogEntity blogEntity) {
        if (blogEntity.getId() == null) {
            blogEntity.setCreateDate(new Date(System.currentTimeMillis()));
        } else {
            blogEntity.setUpdatedDate(new Date(System.currentTimeMillis()));
        }
        return blogRepository.save(blogEntity);
    }

    @Override
    public List<BlogEntity> getAllBlog(int pageIndex, int pageSize, String search) {
        List<BlogEntity> rs = new ArrayList<>();
        if (search == null || search.isEmpty()) {
            rs = blogRepository.findAll(PageRequest.of(pageIndex, pageSize)).getContent();
        } else {
            List<BlogEntity> blogEntities = blogRepository.findAllByTitleContains(search);
            int start = pageIndex * pageSize;
            for (int i = 0; i < blogEntities.size(); i++) {
                if (i >= start && i < start + pageSize) {
                    if (i > start + pageSize) {
                        break;
                    }
                    rs.add(blogEntities.get(i));
                }
            }
        }
        return rs;
    }

    @Override
    public List<BlogEntity> getAllBlogByAuthorId(int pageIndex, int pageSize, Long id) {
        List<BlogEntity> rs = new ArrayList<>();
        List<BlogEntity> blogEntities = blogRepository.findAllByAuthor(id);
        int start = pageIndex * pageSize;
        for (int i = 0; i < blogEntities.size(); i++) {
            if (i >= start && i < start + pageSize) {
                if (i > start + pageSize) {
                    break;
                }
                rs.add(blogEntities.get(i));
            }
        }
        return rs;
    }

    @Override
    public List<BlogEntity> getAllBlogByCategory(int pageIndex, int pageSize, Long categoryId) {
        List<BlogEntity> rs = new ArrayList<>();
        List<BlogEntity> blogEntities = blogRepository.findAllByCategory(categoryId);
        int start = pageIndex * pageSize;
        for (int i = 0; i < blogEntities.size(); i++) {
            if (i >= start && i < start + pageSize) {
                if (i > start + pageSize) {
                    break;
                }
                rs.add(blogEntities.get(i));
            }
        }
        return rs;
    }


}
