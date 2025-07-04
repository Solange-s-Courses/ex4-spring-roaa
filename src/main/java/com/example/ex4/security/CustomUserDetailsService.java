package com.example.ex4.security;

import com.example.ex4.model.User;
import com.example.ex4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service that loads UserDetails based on username from the database.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor-based injection of UserRepository.
     * @param userRepository the repository to find users in the database
     */
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by username and wraps it in a CustomUserDetails object.
     * @param username the username of the user trying to log in
     * @return UserDetails implementation
     * @throws UsernameNotFoundException if the user does not exist
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt
                .map(CustomUserDetails::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username '" + username + "' not found."));
    }
}
