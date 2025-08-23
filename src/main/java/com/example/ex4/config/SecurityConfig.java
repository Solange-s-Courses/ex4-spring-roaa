//package com.example.ex4.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import static org.springframework.security.config.Customizer.withDefaults;
//
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        return http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/**").hasAnyRole("ADMIN", "LECTURER", "STUDENT")
////                        .anyRequest().authenticated()
////                )
////                .formLogin(withDefaults())
////                .build();
////    }
//      @Bean
//      public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//          return http
//            .csrf(csrf -> csrf.disable())
//            .authorizeHttpRequests(auth -> auth
//                    .anyRequest().permitAll() //  מאפשר גישה לכולם ללא התחברות
//            )
//            .build(); // הסרנו את formLogin()
//      }
//}
package com.example.ex4.config;

import com.example.ex4.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.example.ex4.security.CustomLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Security configuration for the application.
 */
@Configuration
public class SecurityConfig {

    @Autowired
    private CustomLoginSuccessHandler loginSuccessHandler;
    /**
     * Password encoder using BCrypt.
     * @return BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Exposes the AuthenticationManager bean.
     * @param config AuthenticationConfiguration
     * @return AuthenticationManager instance
     * @throws Exception if failed
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Defines security filter chain.
     * @param http HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception if failed
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**").permitAll()  // public pages
                        .requestMatchers("/api/favorites/**").hasRole("STUDENT")
                        // Admin-only access
                        .requestMatchers("/admin-panel/**", "/api/**").hasRole("ADMIN")
                        .requestMatchers("/course/{id}").hasRole("LECTURER")
                        .requestMatchers("/courses/**").hasRole("STUDENT")
                        .requestMatchers("/my-courses/**","/profile/**").hasAnyRole("STUDENT", "LECTURER")

                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                                .successHandler(loginSuccessHandler)
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                )

                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error/403")
                )

                .build();
    }
}
