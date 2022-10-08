package com.example.Sharing.controller;

import com.example.Sharing.entity.Article;
import com.example.Sharing.repository.ArticleRepository;
import com.example.Sharing.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FirstController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {

        List<Article> articles = articleService.getAllArticle();
        model.addAttribute("articles", articles);

        return "basic/index";
    }



}
