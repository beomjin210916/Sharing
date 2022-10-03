package com.example.Sharing.api;

import com.example.Sharing.dto.CommentDto;
import com.example.Sharing.entity.Comment;
import com.example.Sharing.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    CommentService commentService;

    @GetMapping("/api/question/{questionId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long questionId){

        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(questionId);


        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);

    }

    // 댓글 생성
    @PostMapping("/api/question/{questionId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long questionId,
                                             @RequestBody CommentDto dto){
        
        // 서비스에게 위임
        CommentDto createdDto = commentService.create(questionId, dto);
        

        return ResponseEntity.status(HttpStatus.OK).body(createdDto);
    }

    // 댓글 수정
    @PatchMapping("/api/question/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto dto){
        // 서비스에게 위임
        CommentDto updatedDto = commentService.update(id, dto);
        // 결과응답
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);

    }

    // 댓글 삭제
    @DeleteMapping("api/question/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id){
        // 서비스에게 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 반환
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }


}
