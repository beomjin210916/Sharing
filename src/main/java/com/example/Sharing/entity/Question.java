package com.example.Sharing.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Getter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성한다
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Question question) {
        if(question.title != null)
            this.title = title;
        if(question.content != null)
            this.content=content;
    }
}
