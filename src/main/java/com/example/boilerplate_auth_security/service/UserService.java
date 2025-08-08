package com.example.boilerplate_auth_security.service;

import com.example.boilerplate_auth_security.dto.request.SignUpRequest;
import com.example.boilerplate_auth_security.entity.User;
import com.example.boilerplate_auth_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    // 회원 가입
    @Transactional
    public void register(SignUpRequest signUpRequest) {

        String email = signUpRequest.getEmail();

        if(userRepository.existsByEmail(email)){
            throw new UsernameNotFoundException("Email Already Exists");
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .nickname(signUpRequest.getNickname())
                .role("USER")
                .build();

        userRepository.save(user);
        log.info("회원가입 성공");
    }



}
