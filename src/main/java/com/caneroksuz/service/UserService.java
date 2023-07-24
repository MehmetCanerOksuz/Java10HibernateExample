package com.caneroksuz.service;

import com.caneroksuz.repository.ICrud;
import com.caneroksuz.repository.UserRepository;
import com.caneroksuz.repository.entity.User;
import com.caneroksuz.utility.HibernateUtility;

import java.util.List;
import java.util.Optional;

public class UserService implements ICrud<User> {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }



    @Override
    public User save(User user) {

        return userRepository.save(user);
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

        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()){
            System.out.println("Boyle bir kullanıcı bulunamdı");
        }
        return user;
    }

    public Optional<User> findByUsername(String username) {
        Optional<User> user=userRepository.findByUsername(username);
        if (user.isEmpty()){
            System.out.println("Boyle bir kullanıcı bulunamdı");
        }
        return user;

    }
}
