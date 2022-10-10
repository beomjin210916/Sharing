package com.example.Sharing.dto;

import com.example.Sharing.entity.User;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@ToString
public class UserDto {

    // 고유번호
    private long id;

    // 사용자명
    private String username;

    // 비밀번호
    private String password;

    // 이메일
    private String email;

    // 권한
    private String role;

    @CreationTimestamp
    private LocalDateTime createdDate;

    public User toEntity() {

        return new User(id, username, password, email, role, createdDate);
    }
}
