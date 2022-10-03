package com.example.Sharing.api;

import com.example.Sharing.dto.QuestionForm;
import com.example.Sharing.entity.Question;
import com.example.Sharing.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class QuestionApiController {

    @Autowired
    private QuestionService questionService;


    // GET
    @GetMapping("/api/questions")
    public List<Question> getQuestions(){

        return questionService.getQuestions();
    }

    @GetMapping("/api/questions/{id}")
    public Question getQuestion(@PathVariable Long id){

        return questionService.getQuestion(id);
    }

    // POST
    @PostMapping("/api/questions/createQuestion")
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionForm dto){

        Question created = questionService.createQuestion(dto);

        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/api/questions/{id}/editQuestion")
    public ResponseEntity<Question> editQuestion(@PathVariable Long id,
                                                 @RequestBody QuestionForm dto){

        Question updated = questionService.editQuestion(id, dto);

        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Question> deleteQuestion(@PathVariable Long id){

        Question deleted = questionService.deleteQuestion(id);

        return (deleted != null)?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

}
