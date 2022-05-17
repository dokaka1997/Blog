package com.example.blog.repository;

import com.example.blog.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<BlogEntity, Long> {

    List<BlogEntity> findAllByTitleContains(String title);

    List<BlogEntity> findAllByAuthor(Long author);

    List<BlogEntity> findAllByCategory(Long category);
}
