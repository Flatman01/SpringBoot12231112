package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<org.springframework.security.core.userdetails.User> findByUsername(String s);
}
