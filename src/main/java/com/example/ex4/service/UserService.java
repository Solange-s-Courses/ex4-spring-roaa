package com.example.ex4.service;

import com.example.ex4.model.User;
import com.example.ex4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling User-related business logic.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Finds a user by ID.
     *
     * @param id the user ID
     * @return Optional containing the user, or empty if not found
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Finds a user by username.
     *
     * @param username the username
     * @return Optional containing the user, or empty if not found
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Returns all users in the system.
     *
     * @return list of all users
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Saves or updates a user.
     *
     * @param user the user to save
     * @return the saved user
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
