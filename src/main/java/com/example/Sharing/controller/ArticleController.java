package com.example.Sharing.controller;

import com.example.Sharing.dto.ArticleForm;
import com.example.Sharing.entity.Article;
import com.example.Sharing.repository.ArticleRepository;
import com.example.Sharing.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

@Controller
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/articles/form")
    public String ArticleShow() {
        return "articles/form";
    }

    @PostMapping("/articles/create")
    // 업로드하는 파일들을 MultipartFile 형태의 파라미터로 전달된다.
    public String createArticle(ArticleForm form, MultipartFile file) throws Exception{

        Article newArticle = articleService.createArticle(form, file);

        return "redirect:/articles/" + newArticle.getId();

    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id: " + id);

        // 1: id로 데이터를 가져옴!
        Article article = articleService.showArticle(id);

        // 2. 가져온 데이터를 모델에 등록!
        model.addAttribute("article", article);


        return "articles/show";
    }

    @GetMapping("/articles/{id}/edit")
    public String showPreviousArticle(@PathVariable Long id, Model model) {
        Article previous = articleService.showArticle(id);

        model.addAttribute("article", previous);

        return "articles/editArticle";

    }

    @PostMapping("/articles/{id}/updateDB")
    public String fixArticle(@PathVariable Long id, ArticleForm form) {

        Article fixed = articleService.editArticle(id, form);

        return "redirect:/articles/" + id;
    }

    @GetMapping("/articles/{id}/deleteArticle")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes rttr) {

        articleService.deleteArticle(id);

        Article article = articleService.showArticle(id);
        if(article != null) {
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        return "redirect:/";
    }


}
