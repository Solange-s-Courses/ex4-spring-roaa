package com.example.ex4.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller to handle errors.
 */
//@Controller
//public class ErrorController {
//
//    @GetMapping("/access-denied")
//    public String accessDenied() {
//        return "error/403";
//    }
//
//    @GetMapping("/pagenotfound")
//    public String pageNotFound() {
//        return "error/404";
//    }
//
//    @GetMapping("/servererror")
//    public String serverError() {
//        return "error/500";
//    }
//
//}
@Controller
public class CustomErrorController implements ErrorController {

    @Bean
    public ErrorController errorController() {
        return new CustomErrorController();
    }


    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error/500";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error/401";
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error/403";
            }

        }

        return "error/default";
    }
}
