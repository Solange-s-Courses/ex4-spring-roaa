package com.example.ex4.service;

import com.example.ex4.model.User;
import com.example.ex4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling User-related business logic.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
        if (user.getId() == null) {
            // יצירה חדשה – הצפנת סיסמה תמיד
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // עדכון – הצפיני רק אם שדה הסיסמה לא ריק
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                // שמר את הסיסמה הקיימת
                String existingPassword = userRepository.findById(user.getId())
                        .map(User::getPassword)
                        .orElseThrow(() -> new IllegalArgumentException("User not found"));
                user.setPassword(existingPassword);
            }
        }

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

    /**
     * Returns Lecturers users in the system.
     *
     * @return list of all users
     */
    public List<User> findLecturers() {
        return userRepository.findLecturers();
    }
}
