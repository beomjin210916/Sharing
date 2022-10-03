package com.example.Sharing.controller;


import com.example.Sharing.dto.CommentDto;
import com.example.Sharing.dto.QuestionForm;
import com.example.Sharing.entity.Article;
import com.example.Sharing.entity.Question;
import com.example.Sharing.repository.QuestionRepository;
import com.example.Sharing.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question")
    public String QuestionList(){

        return "question/questionList";
    }

    @GetMapping("/question/questionForm")
    public String NewQuestion(){

        return "question/questionForm";
    }

    @PostMapping("/question/createNewQuestion")
    public String CreateNewQuestion(QuestionForm form){

        Question question = form.toEntity();

        Question saved = questionRepository.save(question);

        return "redirect:/question/" + saved.getId();
    }

    @GetMapping("/question/{id}")
    public String showQuestion(@PathVariable Long id, Model model){
        log.info("id= " + id);

        Question questionEntity = questionRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        model.addAttribute("question", questionEntity);
        model.addAttribute("commentDtos", commentDtos);

        return "question/showQuestion";

    }

    @GetMapping("/question/questions")
    public String questions(Model model){

        // 1. 모든 질문을 가져온다
        List<Question> questionList = questionRepository.findAll();

        // 2. 가져온 질문 묶음을 뷰로 전달
        model.addAttribute("questionList", questionList);

        // 3. 뷰 페이지(questions)를 설정
        return "question/questions";

    }

    @GetMapping("/question/{id}/editQuestion")
    public String edit(@PathVariable Long id, Model model){

        Question questionEntity = questionRepository.findById(id).orElse(null);

        model.addAttribute("question", questionEntity);

        return "question/editQuestion";
    }

    @PostMapping("/question/updateDB")
    public String updateDB(QuestionForm form){

        Question questionEntity = form.toEntity();

        Question target = questionRepository.findById(questionEntity.getId()).orElse(null);

        if(target!= null) {
            questionRepository.save(questionEntity);
        }

        return "redirect:/question/" + questionEntity.getId();
    }

    @GetMapping("/question/{id}/deleteQuestion")
    public String deleteQuestion(@PathVariable Long id, RedirectAttributes rttr){

        Question target = questionRepository.findById(id).orElse(null);

        if(target != null){
            questionRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다");
        }

        return "redirect:/question/questions";
    }

}


