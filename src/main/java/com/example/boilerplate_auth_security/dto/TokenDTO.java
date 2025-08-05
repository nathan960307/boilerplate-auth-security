package com.example.boilerplate_auth_security.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDTO { // 내부 서비스/비즈니스 로직 전달용 DTO
    private String jti; // jti
    private String accessToken;
    private String refreshToken;
    private GrantType grantType;
    private Long atExpiresIn; // access token 만료 일자
    private Long rtExpiresIn; // refresh token 만료 일자
}
