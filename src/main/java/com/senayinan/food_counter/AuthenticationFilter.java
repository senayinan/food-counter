package com.senayinan.food_counter;

import com.senayinan.food_counter.controllers.AuthenticationController;
import com.senayinan.food_counter.data.UserRepository;
import com.senayinan.food_counter.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Component
public class AuthenticationFilter implements HandlerInterceptor {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationController authenticationController;


    // List of whitelisted paths that don't require authentication
    private static final List<String> whitelist = Arrays.asList("/login", "/register", "/logout", "/css", "/js", "/home");

    // Helper method to check if the requested path is whitelisted
    private static boolean isWhitelisted(String path) {
        for (String pathRoot : whitelist) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }


   /* private static final List<String> blacklist = Arrays.asList("/meals/edit", "/meals/create",
            "/meals/delete", "/foodItem/edit", "/foodItem/create", "/foodItem/delete");
    private static boolean isBlacklisted(String path) {
        for (String pathRoot : blacklist) {
            if (path.startsWith(pathRoot)) {
                return false;
            }
        }
        return true;
    }

    */



    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws IOException {

        // Allow access to whitelisted paths without authentication
        if (isWhitelisted(request.getRequestURI())) {
            return true;
        }



        // Check the user's session
        HttpSession session = request.getSession();
        User user = authenticationController.getUserFromSession(session);

        // The user is logged in, allow access
        if (user != null) {
            return true;
        }

        // The user is NOT logged in, redirect to login page
        response.sendRedirect("/login");
        return false;
    }
}




