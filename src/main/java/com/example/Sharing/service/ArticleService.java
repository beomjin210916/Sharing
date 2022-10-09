package com.example.Sharing.service;

import com.example.Sharing.dto.ArticleForm;
import com.example.Sharing.entity.Article;
import com.example.Sharing.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;
import java.util.UUID;


@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticle() {
        return articleRepository.findAll();
    }


    public Article showArticle(Long id) {

        return articleRepository.findById(id).orElse(null);

    }


    public Article editArticle(Long id, ArticleForm form){

        // 값을 받아와서 고칠 것을 가져옴
        Article willFixedArticle = form.toEntity();
        
        // 예전에 있던 아티클을 불러옴
        Article previousArticle = articleRepository.findById(willFixedArticle.getId()).orElse(null);

        if(previousArticle == null || willFixedArticle.getId() != id){
            return null;
        }

        previousArticle.patch(willFixedArticle);
        Article fixedArticle = articleRepository.save(willFixedArticle);

        return fixedArticle;

    }

    public void deleteArticle(Long id) {


        Article willDeletedArticle = articleRepository.findById(id).orElse(null);

        if(willDeletedArticle != null) {
            articleRepository.delete(willDeletedArticle);
        }


    }


    public Article createArticle(ArticleForm form, MultipartFile file) throws Exception {

        Article article = form.toEntity();

        if(article.getId() != null){
            return null;
        }

        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";

        UUID uuid = UUID.randomUUID();
        String fileName = uuid + "_" + file.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        article.setFileName(fileName);
        article.setFilePath("/images/" + fileName);


        return articleRepository.save(article);

    }


}
