package com.example.SpringBoot.repository;

import com.example.SpringBoot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
