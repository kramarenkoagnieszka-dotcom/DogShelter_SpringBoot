package org.mydomain.shelterspringboot.service;

import org.mydomain.shelterspringboot.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<User> users;


    public void createAndAddUser(String roleChoice, String firstName, String lastName,
                                 String username, String password, String email) {


        User newUser = switch (roleChoice) {
            case "1" -> new Staff(firstName, lastName, username, password, email);
            case "2" -> new Adopter(firstName, lastName, username, password, email);
            case "3" -> new Donor(firstName, lastName, username, password, email);
            case "4" -> new Admin(firstName, lastName, username, password, email);
            default -> throw new IllegalArgumentException("Invalid role selected: " + roleChoice);
        };

        addUser(newUser);
    }

    public void addUser(User newUser) {
        if (users.stream().anyMatch(u -> u.getId().equals(newUser.getId()))) {
            throw new IllegalArgumentException("User with ID " + newUser.getId() + " already exists.");
        }
        if (users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(newUser.getUsername()))) {
            throw new IllegalArgumentException("Username '" + newUser.getUsername() + "' is already taken.");
        }
        if (newUser instanceof Admin && hasAdmin()) {
            throw new IllegalStateException("The system can only have one administrator.");
        }

        users.add(newUser);
    }

    public User login(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
    }

    public void removeUserById(Long id) {
        User userToRemove = findUserById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found."));

        if (userToRemove instanceof Admin) {
            throw new IllegalStateException("The administrator account cannot be deleted.");
        }

        users.remove(userToRemove);
    }

    public Optional<User> findUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();
    }

    private boolean hasAdmin() {
        return users.stream().anyMatch(u -> u instanceof Admin);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}
