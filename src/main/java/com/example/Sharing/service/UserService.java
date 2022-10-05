package com.example.Sharing.service;

import com.example.Sharing.dto.UserDto;
import com.example.Sharing.entity.User;
import com.example.Sharing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    /*
        spring secutiry 필수 메소드 구현

        @param ID 아이디
        @return UserDetails
        @rhows UsernameNotFoundException -> 유저가 없을 때 예외 발생
     */
    @Override // 기본적인 반환 타입은 UserDetails, UserDetails를 상속박은 User로 반환타입 지정
    public User loadUserByUsername(String ID) throws UsernameNotFoundException {
        return userRepository.findByID(ID)
                .orElseThrow(() -> new UsernameNotFoundException((ID)));
    }

    /*
        회원정보 저장
        @param dto 회원정보가 들어있는 DTO
        @return 저장되는 회원의 PK
     */
    public Long save(UserDto dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        dto.setPassword(encoder.encode(dto.getPassword()));

        return userRepository.save(User.builder()
                .ID(dto.getID())
                .auth(dto.getAuth())
                .password(dto.getPassword()).build()).getUserNumber();
    }

}
