package com.example.Sharing.dto;

import com.example.Sharing.entity.Question;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class QuestionForm {


    private Long id;
    private String title;
    private String content;

    public Question toEntity(){
        return new Question(id, title, content);
    }

}
