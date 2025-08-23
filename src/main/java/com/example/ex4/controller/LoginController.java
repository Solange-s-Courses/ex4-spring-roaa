package com.example.ex4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller responsible for handling login page requests.
 */
@Controller
public class LoginController {

    /**
     * Displays the login page.
     *
     * @return the name of the Thymeleaf template for login
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
