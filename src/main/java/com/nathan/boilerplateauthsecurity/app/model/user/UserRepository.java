package com.nathan.boilerplateauthsecurity.app.model.user;


import java.util.Optional;

import com.nathan.boilerplateauthsecurity.app.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}