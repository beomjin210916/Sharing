package com.example.Sharing.dto;


import com.example.Sharing.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private Long id;
    private String title;
    private String people;
    private String gitHubURL;
    private String kakaoURL;
    private String content;

    private String fileName;

    private String filePath;


    public Article toEntity(){
        return new Article(id, title, people, gitHubURL, kakaoURL, content, fileName, filePath);
    }


}
