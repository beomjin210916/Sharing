package com.example.Sharing.dto;


import com.example.Sharing.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {

    private String title;
    private String people;
    private String gitHubURL;
    private String kakaoURL;
    private String content;

    public Article toEntity(){
        return new Article(null, title, people, gitHubURL, kakaoURL, content);
    }

}
