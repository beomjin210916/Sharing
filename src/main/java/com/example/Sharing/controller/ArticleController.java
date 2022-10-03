package com.example.Sharing.controller;

import com.example.Sharing.dto.ArticleForm;
import com.example.Sharing.entity.Article;
import com.example.Sharing.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;


    @GetMapping("/articles/form")
    public String ArticleShow() {
        return "articles/form";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        Article article = form.toEntity();

        Article saved = articleRepository.save(article);

        return "";

    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id: " + id);

        // 1: id로 데이터를 가져옴!
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);


        return "articles/show";
    }


}
