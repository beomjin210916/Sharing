package com.example.Sharing.controller;

import com.example.Sharing.repository.ArticleRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    ArticleRepository articleRepository;

    @GetMapping("/")
    public String index() {


        return "basic/index";
    }



}
