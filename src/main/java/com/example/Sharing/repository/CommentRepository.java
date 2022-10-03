package com.example.Sharing.repository;

import com.example.Sharing.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글의 모든 댓글 조회
    @Query(value =
                    "SELECT * " +
                    "FROM comment " +
                    "WHERE question_id = :questionId", nativeQuery = true)
    List<Comment> findByQuestionId(Long questionId); // Long뒤의 변수는 :뒤의 변수와 같아야 함(입력값)



    // 특정 닉네임의 모든 댓글 조회
    List<Comment> findByNickname(String nickname);

}
