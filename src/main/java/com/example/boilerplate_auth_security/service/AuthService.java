package com.example.boilerplate_auth_security.service;

import com.example.boilerplate_auth_security.dto.TokenDTO;
import com.example.boilerplate_auth_security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public void authenticateMember(String email, String password) {
        // 인증된 사용자 정보 등록용 토큰 생성
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password); // 인증되지 않은 토큰
        Authentication authentication = authenticationManager.authenticate(authToken); // 인증 객체 생성
        SecurityContextHolder.getContext().setAuthentication(authentication); // 보안 컨텍스트 저장
    }


    // 자체 로그인
    public TokenDTO login(String email, String password) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password); // 인증되지 않은 토큰
        Authentication authentication = authenticationManager.authenticate(authToken); // 인증 객체 생성( 유저 확인, 비번 검증까지 함)


        String accessToken = jwtTokenProvider.generateAccessToken(email); // access token 생성
        String refreshToken = jwtTokenProvider.generateRefreshToken(email); // refresh token 생성

        TokenDTO tokenDTO = TokenDTO.builder()
                .accessToken(accessToken)
                .atExpiresIn(jwtTokenProvider.getAtExpiration()) // access token의 실제 만료일자가 아닌 설정된 만료 시간
                .refreshToken(refreshToken)
                .build();

        return tokenDTO;
    }
}
