package com.example.blog.service.Impl;

import com.example.blog.entity.BlogEntity;
import com.example.blog.repository.BlogRepository;
import com.example.blog.service.BlogService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<BlogEntity> getAllBlog(int pageIndex, int pageSize, String search, Long author, Long category) {
        List<BlogEntity> rs = new ArrayList<>();

        List<BlogEntity> blogEntities = blogRepository.findAll();
        if (search.isEmpty()) {
            rs.addAll(blogEntities);
        } else {
            for (BlogEntity blogEntity : blogEntities) {
                if (blogEntity.getTitle().toLowerCase().contains(search.toLowerCase())) {
                    rs.add(blogEntity);
                }
            }
        }

        List<BlogEntity> finalRs = new ArrayList<>();
        int start = pageIndex * pageSize;
        for (int i = 0; i < rs.size(); i++) {
            boolean isAdd = false;
            if (i >= start && i < start + pageSize) {
                if (i > start + pageSize) {
                    break;
                }
                if (author == null && category == null) {
                    finalRs.add(blogEntities.get(i));
                    continue;
                }
                if (author != null && Objects.equals(rs.get(i).getAuthor(), author)) {
                    isAdd = true;
                }
                if (category != null && !Objects.equals(rs.get(i).getCategory(), category)) {
                    isAdd = false;
                }

                if (isAdd) {
                    finalRs.add(blogEntities.get(i));
                }
            }
        }
        return finalRs;
    }

}
