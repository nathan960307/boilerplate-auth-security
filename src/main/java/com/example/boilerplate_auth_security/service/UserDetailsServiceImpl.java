package com.example.boilerplate_auth_security.service;

import com.example.boilerplate_auth_security.entity.Member;
import com.example.boilerplate_auth_security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService { // UserDetailsService 구현체

    private final UserRepository userRepository;

    // 로그인 사용자 확인 및 권한 설정
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("탈퇴했거나 존재하지 않는 사용자입니다."));

        // member 객체에서 직접 권한 추출
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(member.getRole()));

        return Principal.createPrincipal(member, authorities);
    }
}
