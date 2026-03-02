package com.app.ecommerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.ecommerce.dto.AuthResponse;
import com.app.ecommerce.entity.User;
import com.app.ecommerce.security.JwtUtil;
import com.app.ecommerce.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody User request) {
        User user = userService.login(request.getEmail(), request.getPassword());
        String token = jwtUtil.generateToken(
            user.getEmail(),
            user.getRole().name()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }

}