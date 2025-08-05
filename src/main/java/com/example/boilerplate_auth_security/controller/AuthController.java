package com.example.boilerplate_auth_security.controller;


import com.example.boilerplate_auth_security.dto.TokenDTO;
import com.example.boilerplate_auth_security.dto.request.LoginRequestDTO;
import com.example.boilerplate_auth_security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(
            @RequestBody LoginRequestDTO request) {

        // TODO: 인증 로직 (아이디/비밀번호 확인) 추가 예정

        // 인증 성공 가정, userId는 이메일로 대체
        TokenDTO tokenDTO = jwtTokenProvider.generateAccessToken(request.getEmail());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tokenDTO);
    }
}
