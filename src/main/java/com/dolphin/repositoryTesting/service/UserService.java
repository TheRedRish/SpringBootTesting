package com.dolphin.repositoryTesting.service;

import com.dolphin.repositoryTesting.model.User;
import com.dolphin.repositoryTesting.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> fetchAll() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            User userToUpdate = optionalUser.get();
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setDateOfBirth(user.getDateOfBirth());
            userToUpdate.setRole(user.getRole());
            userRepository.save(userToUpdate);
        } else {
            throw new RuntimeException("User not found with id: " + user.getId());
        }
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}