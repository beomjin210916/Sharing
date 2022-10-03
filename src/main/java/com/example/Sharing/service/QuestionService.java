package com.example.Sharing.service;

import com.example.Sharing.dto.ArticleForm;
import com.example.Sharing.dto.QuestionForm;
import com.example.Sharing.entity.Question;
import com.example.Sharing.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Slf4j
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    public List<Question> getQuestions(){

        return questionRepository.findAll();
    }

    public Question getQuestion(@PathVariable Long id){

        return questionRepository.findById(id).orElse(null);
    }

    // POST
    public Question createQuestion(QuestionForm dto){
        Question question = dto.toEntity();
        if (question.getId() != null){
            return null;
        }
        return questionRepository.save(question);
    }

    // PATCH
    public Question editQuestion(@PathVariable Long id, @RequestBody QuestionForm dto){

        // 1. 수정용 entity
        Question question = dto.toEntity();

        // 2. 대상 엔티티를 조회
        Question target = questionRepository.findById(question.getId()).orElse(null);

        // 3. 잘못된 요청 처리
        if(target == null || id != question.getId()){
            // 400 잘못된 요청 응답!
            log.info("잘못된 요청! id:{}, question:{}", id, question.toString());
            return null;
        }

        target.patch(question);
        Question updated = questionRepository.save(question);
        return updated;

    }

    // DELETE
    public Question deleteQuestion(@PathVariable Long id){

        Question target = questionRepository.findById(id).orElse(null);


        if(target == null) {
            return null;
        }

        questionRepository.delete(target);
        return target;

    }

}
