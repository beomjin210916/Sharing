package com.example.Sharing.config;

import com.example.Sharing.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@Configuration // spring security를 활성화한다는 의미의 어노테이션
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { // spring security의 설정파일로써 상속해야 하는 클래스
    private final UserService userService; // 유저 정보를 가져올 클래스

    @Override
    public void configure(WebSecurity webSecurity){ // 상속받은 걸 오버라이드
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/images/**"); // css 나 images, js 가 있다면 js를 무조건
        // 접근이 가능해야 해서 인증을 무시하게 만들어 버린다. static 하위 폴더에 있는 것만 가능
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{ // http 관련 인증 설정이 가능하게 된다
        http
                .authorizeHttpRequests() // 접근에 대한 인증 설정이 가능하게 만들어준다
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN 만 접근 가능
                .anyRequest().permitAll()// 나머지 요청들은 권한의 종류에 상관없이 권한이 있어야 접근 가능
                
                .and()
                 .formLogin() // 로그인에 관한 설정을 의미한다
                    .loginPage("/login/login") // 로그인 페이지 링크
                    .defaultSuccessUrl("/basic/index") // 성공후 URL 주소
                
                .and()
                 .logout() //로그아웃에 관한 설정을 의미한다
                    .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                    .invalidateHttpSession(true) // 세션 날리기
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { // 로그인할 때 필요한 정보를 가져오는 곳이다
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
        /* 해당 서비스 에서는 UserDetailsService를 implements해서 loadUserByUsername()을 구현해야 함
        * */
    }

}
