package com.example.Sharing.controller;


import com.example.Sharing.entity.Article;
import com.example.Sharing.entity.User;
import com.example.Sharing.repository.UserRepository;
import com.example.Sharing.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
public class LoginController {
    @Autowired
    ArticleService articleService;
    @Autowired private UserRepository userRepository; // 글 아래에서 생성할 예정
    @Autowired private BCryptPasswordEncoder passwordEncoder; // 시큐리티에서 빈(Bean) 생성할 예정

    /**
     * 유저 페이지
     *
     * @return
     */

    @GetMapping("user")
    public String user() {
        return "user";
    }

    /**
     * 로그인 폼 페이지
     *
     * @return
     */
    @GetMapping("login/login")
    public String loginForm() {
        return "login/login";
    }

    /**
     * 회원 가입 페이지
     *
     * @return
     */
    @GetMapping("/login/joinForm")
    public String joinForm() {
        return "login/signup";
    }

    /**
     * 회원 가입이 실행되는 부분
     *
     * @param user
     * @return
     */
    @PostMapping("join")
    public String join(User user) {

        user.setRole("ROLE_USER"); // 권한 정보는 임시로 ROLE_ADMIN으로 넣는다.
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login/" + user.getId() + "/userIndex";
    }

    @GetMapping("/login/{id}/userIndex")
    public String userIndex(@PathVariable Long id, Model model) {

        User usingUser = userRepository.findById(id).orElse(null);

        List<Article> articles = articleService.getAllArticle();
        model.addAttribute("articles", articles);

        model.addAttribute("usingUser", usingUser);

        return "basic/userIndex";
    }
}
