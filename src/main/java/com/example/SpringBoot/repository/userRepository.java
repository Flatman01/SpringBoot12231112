package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface userRepository extends JpaRepository<User, Long> {
}
