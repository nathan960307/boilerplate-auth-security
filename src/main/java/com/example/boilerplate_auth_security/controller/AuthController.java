package com.example.boilerplate_auth_security.controller;


import com.example.boilerplate_auth_security.dto.TokenDTO;
import com.example.boilerplate_auth_security.dto.request.LoginRequestDTO;
import com.example.boilerplate_auth_security.dto.request.SignUpRequest;
import com.example.boilerplate_auth_security.service.AuthService;
import com.example.boilerplate_auth_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    // 자체 로그인
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO) {

        TokenDTO tokenDTO = authService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tokenDTO);
    }

    // 자체 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @RequestBody SignUpRequest signUpRequest) {

        userService.register(signUpRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("회원가입 완료");

    }



}
