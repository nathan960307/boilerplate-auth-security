package com.example.boilerplate_auth_security.jwt;

import com.example.boilerplate_auth_security.dto.TokenDTO;
import com.example.boilerplate_auth_security.entity.Member;
import com.example.boilerplate_auth_security.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * [ JwtTokenProvider ]
 * 1. AccessToken 생성
 * 2. RefreshToken 생성
 * 3. 토큰 서명(Sign) 및 암호화 키 관리
 * 4. 토큰 만료 시간 관리
 * 5. 토큰 검증 (서명, 만료 여부, 포맷)
 * 6. Claims 추출 (sub, role 등)
 * 7. jti(UUID) 생성
 * */

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final UserRepository userRepository;

    @Getter
    @Value("${jwt.access-expiration}")
    private long atExpiration; // access token 만료 시간

    @Getter
    @Value("${jwt.refresh-expiration}")
    private long rtExpiration; // refresh token 만료 시간

    @Value("${jwt.secret}")
    private String key;

    private SecretKey secretKey;


    /**
     * 1. key 문자열을 바이트 문자열로 변환
     * 2. 해당 바이트 배열을 기반으로 HMAC-SHA 알고리즘에 필요한 비밀키 객체를 생성
     *
     * 입력한 key 값이 내부적으로 암호학적 난수(비밀키) 역할을 하도록 변환되며,
     * JWT 서명 과 검증에 안전하게 쓰이는 SecretKey 객체가 됨
     * */
    // 비밀 키 만들기
    public SecretKey getSecretKey(){
        if(secretKey == null){
            secretKey  = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        }
        return secretKey;
    }

    // access token 생성
    public String generateAccessToken(String email){

        String jti = UUID.randomUUID().toString(); // jti

        long now = new Date().getTime();
        Date atExpiresIn = new Date(now + atExpiration); // 만료일자 생성

        // 이메일로 사용자 객체 가져오기
        Member member = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("탈퇴했거나 존재하지 않는 사용자입니다."));

        List<String> authorities = List.of(member.getRole());

        String accessToken = Jwts.builder()
                .subject(email)
                .id(jti) // jti
                .issuedAt(new Date(now)) // 생성일자
                .expiration(atExpiresIn) // 만료일자
                .claim("authorities", authorities)
                .signWith(getSecretKey()) // 서명
                .compact();

//        TokenDTO tokenDto = TokenDTO.builder()
//                .jti(jti) // jti
//                .accessToken(accessToken)
//                .atExpiresIn(atExpiration)
//                .build();
//
//        return tokenDto;
        return accessToken;
    }

    // refresh token 생성
    public String generateRefreshToken(String email){

        String jti = UUID.randomUUID().toString(); // jti

        long now = new Date().getTime();
        Date rtExpiresIn = new Date(now + rtExpiration); // 만료일자 생성

        String refreshToken = Jwts.builder()
                .subject(email)
                .id(jti) // jti
                .issuedAt(new Date(now)) // 생성일자
                .expiration(rtExpiresIn) // 만료일자
                .signWith(getSecretKey()) // 서명
                .compact();

        return refreshToken;

    }



    // token 유효성 검사 (서명 위조, 만료 등)
    public boolean validateToken(String token) {
        try {

            Jwts.parser().verifyWith(getSecretKey()).build().parse(token); // 유효성 검사

            return true;

        }catch(SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException | ExpiredJwtException e){

            log.error("❌ JWT 유효성 검사 실패: " + e.getClass().getSimpleName() + " - " + e.getMessage());

            e.printStackTrace();

            throw e;
        }
    }

    // Token 안에 있는 Claims (페이로드) 꺼내기
    public Claims parseClaim(String token) {
        try {
            return Jwts.parser().verifyWith(getSecretKey()).build()
                    .parseSignedClaims(token).getPayload();
        } catch (ExpiredJwtException ex) {
            return ex.getClaims();
        }
    }




}
