package com.example.boilerplate_auth_security.service;

import com.example.boilerplate_auth_security.dto.TokenDTO;
import com.example.boilerplate_auth_security.entity.User;
import com.example.boilerplate_auth_security.jwt.JwtTokenProvider;
import com.example.boilerplate_auth_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TokenDTO login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(email);
        String refreshToken = jwtTokenProvider.generateRefreshToken(email);

        TokenDTO tokenDTO = TokenDTO.builder()
                .accessToken(accessToken)
                .atExpiresIn(jwtTokenProvider.getAtExpiration()) // access token의 실제 만료일자가 아닌 설정된 만료 시간
                .refreshToken(refreshToken)
                .build();

        log.info("로그인 성공");
        return tokenDTO;
    }
}
