//package com.example.ex4.controller;
//
//import com.example.ex4.model.User;
//import com.example.ex4.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
///**
// * Controller for managing users.
// */
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    /**
//     * Get all users.
//     */
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.findAll();
//    }
//
//    /**
//     * Get user by ID.
//     */
//    @GetMapping("/{id}")
//    public Optional<User> getUserById(@PathVariable Long id) {
//        return userService.findById(id);
//    }
//
//    /**
//     * Get user by username.
//     */
//    @GetMapping("/by-username/{username}")
//    public Optional<User> getUserByUsername(@PathVariable String username) {
//        return userService.findByUsername(username);
//    }
//
//    /**
//     * Create a new user.
//     */
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.save(user);
//    }
//
//    /**
//     * Update an existing user.
//     */
//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//        return userService.findById(id)
//                .map(user -> {
//                    user.setUsername(updatedUser.getUsername());
//                    user.setFullName(updatedUser.getFullName());
//                    user.setRole(updatedUser.getRole());
//                    user.setPassword(updatedUser.getPassword());
//                    return userService.save(user);
//                })
//                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
//    }
//
//    /**
//     * Delete a user.
//     */
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteById(id);
//    }
//}
package com.example.ex4.controller;

import com.example.ex4.model.User;
import com.example.ex4.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for managing users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Get all users.
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    /**
     * Get user by ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get user by username.
     */
    @GetMapping("/by-username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Create a new user.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    /**
     * Update an existing user.
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser) {
        return userService.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    user.setFullName(updatedUser.getFullName());
                    user.setRole(updatedUser.getRole());
                    user.setPassword(updatedUser.getPassword());
                    return ResponseEntity.ok(userService.save(user));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete a user.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
