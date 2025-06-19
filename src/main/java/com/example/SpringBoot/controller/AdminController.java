package com.example.SpringBoot.controller;

import com.example.SpringBoot.Service.UserService;
import com.example.SpringBoot.model.Role;
import com.example.SpringBoot.model.User;
import com.example.SpringBoot.repository.RoleRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/users")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/list";  // список пользователей
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/create";  // форма создания
    }

    @GetMapping("/admin/users/new")
    public String createUserForm(Model model) {
        User user = new User();
        user.setRoles(new HashSet<>()); // Пустой Set, чтобы roles не было null
        model.addAttribute("user", user);
        model.addAttribute("roles", roleRepository.findAll()); // список ролей
        return "admin/create";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        model.addAttribute("roles", roleRepository.findAll());
        return "admin/edit";  // форма редактирования
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute("user") User updatedUser,
                             @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        User existingUser = userService.findById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());

        if (roleIds != null) {
            Set<Role> roles = roleRepository.findAllById(roleIds).stream().collect(Collectors.toSet());
            existingUser.setRoles(roles);
        } else {
            existingUser.setRoles(Collections.emptySet());
        }

        userService.save(existingUser);
        return "redirect:/admin/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public String saveUser(@ModelAttribute User user,
                           @RequestParam(value = "roles", required = false) List<Long> roleIds) {
        if (roleIds != null) {
            Set<Role> roles = roleRepository.findAllById(roleIds).stream().collect(Collectors.toSet());
            user.setRoles(roles);
        } else {
            user.setRoles(Collections.emptySet());
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

}
