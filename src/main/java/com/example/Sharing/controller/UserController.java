package com.example.Sharing.controller;

import com.example.Sharing.dto.UserDto;
import com.example.Sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {

    private UserService userService;

    @PostMapping("/user")
    public String signup(UserDto dto) {
        userService.save(dto);
        return "redirect:/login";
    }

}
