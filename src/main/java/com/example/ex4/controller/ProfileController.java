package com.example.ex4.controller;

import com.example.ex4.model.User;
import com.example.ex4.service.UserService;
import com.example.ex4.session.UserSession;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for displaying and updating the profile page of a logged-in user.
 * <p>
 * Supports viewing user details and changing the password securely.
 */
@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Resource
    private UserService userService;

    @Resource
    private UserSession userSession;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Displays the profile page of the currently logged-in user.
     *
     * @param model Spring MVC model to inject attributes into the view
     * @return the name of the profile view template
     */
    @GetMapping
    public String showProfile(Model model) {
        Long userId = userSession.getUserId();
        User user = userService.findById(userId).orElse(null);

        model.addAttribute("user", user);
        return "profile";
    }

    /**
     * Handles password change requests from the profile page.
     *
     * @param currentPassword the current password provided by the user
     * @param newPassword the new password to be set
     * @param model Spring MVC model used to pass data to the view
     * @return the name of the profile view, with error/success messages if applicable
     */
    @PostMapping("/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 Model model) {
        Long userId = userSession.getUserId();
        User user = userService.findById(userId).orElse(null);

        if (user == null || !passwordEncoder.matches(currentPassword, user.getPassword())) {
            model.addAttribute("error", "Incorrect current password.");
            model.addAttribute("user", user);
            return "profile";
        }


        user.setPassword(passwordEncoder.encode(newPassword));
        userService.save(user);

        model.addAttribute("success", true);
        model.addAttribute("user", user);
        return "profile";
    }
}
