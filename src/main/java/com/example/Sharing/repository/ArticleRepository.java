package com.example.Sharing.repository;

import com.example.Sharing.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();

    Page<Article> findByTitleContaining(String keyword, Pageable pageable);
}
