package com.example.Sharing.entity;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // 사용자명
    @Column
    private String username;

    // 비밀번호
    @Column
    private String password;

    // 이메일
    @Column
    private String email;

    // 권한
    @Column
    private String role;

    // 계정 생성일
    @CreationTimestamp private LocalDateTime createdDate;
}
