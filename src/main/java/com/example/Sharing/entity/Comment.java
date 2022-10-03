package com.example.Sharing.entity;


import com.example.Sharing.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 이것을 함으로써 이 키를 Question 객체의 외래키로 쓴다 id 값을 가져옴
    @JoinColumn(name = "question_id")
    private Question question;

    @Column
    private String nickname;

    @Column
    private String body;


    public static Comment createComment(CommentDto dto, Question question) {

        // 예외 처리
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 존재함");
        if (dto.getQuestionId() != question.getId())
            throw new IllegalArgumentException("댓글 생성 실패, 게ㅔ시글의 id가 잘못되었음");

        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(),
                question,
                dto.getNickname(),
                dto.getBody()
        );


    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("잘못된 id가 입력되었음");

        // 객체를 갱신
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
        
    }
}
