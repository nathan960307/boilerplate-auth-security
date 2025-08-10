package com.example.boilerplate_auth_security.repository;

import com.example.boilerplate_auth_security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member,Long> {

    boolean existsByEmail(String email); // 이메일 중복 체크
    Optional<Member> findByEmail(String email);

    String email(String email);
}
