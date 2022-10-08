package com.example.Sharing.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String people;

    @Column
    private String gitHubURL;

    @Column
    private String kakaoURL;

    @Column
    private String content;

    @Column
    private String fileName;

    @Column
    private String filePath;


    public void patch(Article fixedArticle) {

        if(fixedArticle.title != null) this.title = fixedArticle.getTitle();
        if(fixedArticle.title != null) this.people = fixedArticle.getPeople();
        if(fixedArticle.title != null) this.gitHubURL = fixedArticle.getGitHubURL();
        if(fixedArticle.title != null) this.kakaoURL = fixedArticle.getKakaoURL();
        if(fixedArticle.title != null) this.content = fixedArticle.getContent();
        if(fixedArticle.title != null) this.fileName = fixedArticle.getFileName();
        if(fixedArticle.title != null) this.filePath = fixedArticle.getFilePath();

    }
}
