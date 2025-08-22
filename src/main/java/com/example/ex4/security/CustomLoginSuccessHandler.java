//package com.example.ex4.security;
//
//import com.example.ex4.model.User;
//import com.example.ex4.session.UserSession;
//import com.example.ex4.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserSession userSession;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException {
//        String username = authentication.getName();
//        User user = userRepository.findByUsername(username).orElse(null);
//
//
//        if (user != null) {
//            userSession.setUserId(user.getId());
//            userSession.setUsername(user.getUsername());
//            userSession.setRole(user.getRole().name());
//        }
//
//        response.sendRedirect("/my-courses"); // או כל דף שתרצי להפנות אליו אחרי התחברות
//    }
//}
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

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSession userSession;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            userSession.setUserId(user.getId());
            userSession.setUsername(user.getUsername());
            userSession.setRole(user.getRole().name());

            // הפניה לפי תפקיד
            switch (user.getRole()) {
                case ADMIN -> response.sendRedirect("/admin-panel");
                case STUDENT -> response.sendRedirect("/my-courses");
                case LECTURER -> response.sendRedirect("/my-courses"); // שימי כאן את הדף המתאים אם יש
                default -> response.sendRedirect("/my-courses");
            }
        } else {
            // אם משום מה לא מצאנו את המשתמש – להפנות לדף שגיאה כללי
            response.sendRedirect("/error/500");
        }
    }
}
