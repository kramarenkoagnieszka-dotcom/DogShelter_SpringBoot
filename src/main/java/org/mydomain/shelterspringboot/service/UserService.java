package org.mydomain.shelterspringboot.service;

import org.mydomain.shelterspringboot.model.User;
import org.mydomain.shelterspringboot.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void addUser(User newUser) {
        if (userRepository.existsByUsernameIgnoreCase(newUser.getUsername())) {
            throw new IllegalArgumentException("Username '" + newUser.getUsername() + "' is already taken.");
        }
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new IllegalArgumentException("Email '" + newUser.getEmail() + "' is already registered.");
        }

        userRepository.save(newUser);
    }

    @Transactional
    public void removeUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
        userRepository.deleteById(id);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> searchByLastName(String lastNamePart) {
        return userRepository.findByLastNameContainingIgnoreCase(lastNamePart);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
