package com.example.ex4.security;

import com.example.ex4.model.User;
import com.example.ex4.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Custom implementation of UserDetails to integrate application-specific User entity with Spring Security.
 */
public class CustomUserDetails implements UserDetails {

    private final User user;

    /**
     * Constructor that wraps the application User entity.
     * @param user the application-specific user entity
     */
    public CustomUserDetails(User user) {
        this.user = user;
    }

    /**
     * Returns the authorities granted to the user. In our case, a single role.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * Indicates whether the user's account has expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the wrapped User entity.
     */
    public User getUser() {
        return user;
    }
}
