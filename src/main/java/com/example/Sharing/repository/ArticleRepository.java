package com.example.Sharing.repository;

import com.example.Sharing.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Override
    ArrayList<Article> findAll();
}
