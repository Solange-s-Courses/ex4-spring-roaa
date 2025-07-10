package com.example.ex4.repository;

import com.example.ex4.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for accessing User data.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Finds a user by username.
     *
     * @param username the username to search for
     * @return Optional containing the user if found, empty otherwise
     */
    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.role = com.example.ex4.model.Role.LECTURER")
    List<User> findLecturers();

}
