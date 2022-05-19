package com.example.blog.service.Impl;

import com.example.blog.entity.BlogEntity;
import com.example.blog.entity.CategoryEntity;
import com.example.blog.entity.UserEntity;
import com.example.blog.model.response.BlogDTO;
import com.example.blog.repository.BlogRepository;
import com.example.blog.repository.CategoryRepository;
import com.example.blog.repository.UserRepository;
import com.example.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    BlogRepository blogRepository;

    UserRepository userRepository;

    CategoryRepository categoryRepository;

    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
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
    public List<BlogDTO> getAllBlog(int pageIndex, int pageSize, String search, Long author, Long category) {
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
                if (category != null && Objects.equals(rs.get(i).getCategory(), category)) {
                    isAdd = true;
                }

                if (isAdd) {
                    finalRs.add(blogEntities.get(i));
                }
            }
        }
        List<BlogDTO> blogDTOS = new ArrayList<>();
        for (BlogEntity entity : finalRs) {
            BlogDTO dto = new BlogDTO();
            dto.setId(entity.getId());
            dto.setCreateDate(entity.getCreateDate());
            dto.setUpdatedDate(entity.getUpdatedDate());
            if (entity.getAuthor() != null) {
                dto.setAuthor(entity.getAuthor());
                Optional<UserEntity> optionalUser = userRepository.findById(entity.getAuthor());
                optionalUser.ifPresent(userEntity -> dto.setAuthorName(userEntity.getName()));
            }

            if (entity.getCategory() != null) {
                dto.setCategory(entity.getCategory());
                Optional<CategoryEntity> optionalCategory = categoryRepository.findById(entity.getCategory());
                optionalCategory.ifPresent(categoryEntity -> dto.setCategoryName(categoryEntity.getCategoryName()));
            }
            dto.setImageId(entity.getImageId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
            blogDTOS.add(dto);
        }

        return blogDTOS;
    }

    @Override
    public BlogDTO getBlogById(Long id) {
        BlogDTO dto = new BlogDTO();
        Optional<BlogEntity> optionalBlog = blogRepository.findById(id);
        if (optionalBlog.isPresent()) {
            BlogEntity entity = optionalBlog.get();
            dto.setId(entity.getId());
            dto.setCreateDate(entity.getCreateDate());
            dto.setUpdatedDate(entity.getUpdatedDate());
            if (entity.getAuthor() != null) {
                dto.setAuthor(entity.getAuthor());
                Optional<UserEntity> optionalUser = userRepository.findById(entity.getAuthor());
                optionalUser.ifPresent(userEntity -> dto.setAuthorName(userEntity.getName()));
            }

            if (entity.getCategory() != null) {
                dto.setCategory(entity.getCategory());
                Optional<CategoryEntity> optionalCategory = categoryRepository.findById(entity.getCategory());
                optionalCategory.ifPresent(categoryEntity -> dto.setCategoryName(categoryEntity.getCategoryName()));
            }
            dto.setImageId(entity.getImageId());
            dto.setTitle(entity.getTitle());
            dto.setContent(entity.getContent());
        }
        return dto;
    }

    @Override
    public Boolean deleteById(Long id) {
        try {
            blogRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
