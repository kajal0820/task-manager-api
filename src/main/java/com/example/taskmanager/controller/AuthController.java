package com.example.taskmanager.controller;

import com.example.taskmanager.model.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.security.JwtUtil;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repo,
                          PasswordEncoder encoder,
                          JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        return repo.save(user);
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        System.out.println("LOGIN API HIT");
        Optional<User> optionalUser =
                repo.findByUsername(user.getUsername());

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User dbUser = optionalUser.get();

        if (encoder.matches(
                user.getPassword(),
                dbUser.getPassword())) {

            return jwtUtil.generateToken(
                    dbUser.getUsername());
        }

        throw new RuntimeException("Invalid password");
    }
}