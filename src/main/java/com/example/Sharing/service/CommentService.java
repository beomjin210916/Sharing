package com.example.Sharing.service;

import com.example.Sharing.dto.CommentDto;
import com.example.Sharing.entity.Comment;
import com.example.Sharing.entity.Question;
import com.example.Sharing.repository.CommentRepository;
import com.example.Sharing.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<CommentDto> comments(Long questionId) {

        /*
        // 조회: 댓글 목록
        List<Comment> comments = commentRepository.findByQuestionId(questionId);

        // 변환: 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for(int i = 0; i < comments.size(); i++){
            Comment c = comments.get(i); // comments 에 담긴 첫번째 객체를 db에서 불러옴
            CommentDto dto = CommentDto.createCommentDto(c); // dto에 createCommentdto메소드를 사용하여 꺼내온 comment c 를 담음
            dtos.add(dto); // dtos 리스트에 dto를 담는다
        }
        return dtos;
        */

        return commentRepository.findByQuestionId(questionId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());

    }

    @Transactional
    public CommentDto create(Long questionId, CommentDto dto) {

        // 게시글 조회 및 예외 처리
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패. 게시글이 없음"));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, question);
        
        // 댓글 엔티티를 db로 저장
        Comment created = commentRepository.save(comment);
        
        // dto로 변경하여 반환
        return CommentDto.createCommentDto(created);

    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {

        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않음"));
        
        // 댓글 수정
        target.patch(dto);

        // DB로 갱신
        Comment updated = commentRepository.save(target);
        
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);

    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회(및 예외 발생)
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없음"));

        // 댓글 DB에서 삭제
        commentRepository.delete(target);

        // 삭제 댓글을 DTO로 반환
        return CommentDto.createCommentDto(target);
    }
}
