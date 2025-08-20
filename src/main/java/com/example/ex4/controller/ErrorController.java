package com.example.ex4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to handle errors.
 */
@Controller
public class ErrorController {

    @GetMapping("/error/403")
    public String accessDenied() {
        return "error/403";
    }

    @GetMapping("/error/404")
    public String notFound() {
        return "error/404";
    }

    @GetMapping("/error/500")
    public String serverError() {
        return "error/500";
    }

    @GetMapping("/error/401")
    public String unauthorized() {
        return "error/401";
    }

    @GetMapping("/error/default")
    public String defaultError() {
        return "error/default";
    }
}

//@Controller
//public class CustomErrorController implements ErrorController {
//
//    @RequestMapping("/error")
//    public String handleError(HttpServletRequest request, Model model) {
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//
//        if (status != null) {
//            int statusCode = Integer.parseInt(status.toString());
//
//            if (statusCode == HttpStatus.NOT_FOUND.value()) {
//                return "/error/404";
//            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//                return "/error/500";
//            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
//                return "/error/401";
//            }
//            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
//                return "/error/403";
//            }
//
//        }
//
//        return "/error/default";
//    }
//}
