package com.caneroksuz.controller;

import com.caneroksuz.repository.ICrud;
import com.caneroksuz.repository.entity.User;
import com.caneroksuz.service.UserService;


import java.util.List;
import java.util.Optional;

public class UserController implements ICrud<User> {

    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    @Override
    public User save(User user) {
        return userService.save(user);
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public User deleteById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {

        return userService.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {

        return userService.findById(id);
    }

    public Optional<User> findByUsername(String username) {

        return userService.findByUsername(username);
    }
}
