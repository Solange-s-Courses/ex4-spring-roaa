package com.example.ex4.security;

import com.example.ex4.model.User;
import com.example.ex4.session.UserSession;
import com.example.ex4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Custom login success handler that sets session attributes upon successful authentication,
 * and redirects the user to the appropriate page based on their role.
 */
@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSession userSession;

    /**
     * Called when a user has been successfully authenticated.
     * Sets user session attributes (id, username, role) and redirects to appropriate home page.
     *
     * @param request        the HTTP request
     * @param response       the HTTP response
     * @param authentication the authentication object containing user details
     * @throws IOException if redirection fails
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            userSession.setUserId(user.getId());
            userSession.setUsername(user.getUsername());
            userSession.setRole(user.getRole().name());


            switch (user.getRole()) {
                case ADMIN -> response.sendRedirect("/admin-panel");
                case STUDENT -> response.sendRedirect("/my-courses");
                case LECTURER -> response.sendRedirect("/my-courses");
                default -> response.sendRedirect("/my-courses");
            }
        } else {

            response.sendRedirect("/error/500");
        }
    }
}
