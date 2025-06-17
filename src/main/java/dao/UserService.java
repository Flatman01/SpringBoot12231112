package com.example.SpringBoot.Service;

import com.example.SpringBoot.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    void save(User user);
    void delete(Long id);
    void update(User user);
}