package com.example.Sharing.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Getter
public class User implements UserDetails {

    @Id
    @Column(name = "userNumber")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long userNumber;

    @Column(name = "ID", unique = true)
    private String ID;

    @Column(name = "password")
    private String password;

    @Column(name = "auth")
    private String auth;

    @Builder
    public User(String ID, String password, String auth){
        this.ID = ID;
        this.password = password;
        this.auth = auth;
    }

    // 사용자의 권한을 콜렉션 형태로 반환
    // 단 클래스 자료형은 GrantedAuthority 를 구현해야 함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        for (String role: auth.split(",")) {
            roles.add(new SimpleGrantedAuthority(role));
        }
        return roles;
    }

    @Override
    public String getUsername() {
        return ID;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않음. nonexpired니까
    }
    
    @Override
    public boolean isAccountNonLocked() {
        // 계쩡 잠금되었는지 확인하는 로직
        return true; // true -> 잠기지 않았음
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // -> 아직 만료되지 않았음
    }
    
    @Override
    public boolean isEnabled() {
        // 계정을 사용 가능한지 여부 반환
        return true; // -> 아직 사용 가능
    }

}
