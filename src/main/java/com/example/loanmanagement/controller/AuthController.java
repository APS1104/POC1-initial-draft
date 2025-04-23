package com.example.loanmanagement.controller;

import com.example.loanmanagement.dto.AuthRequestDto;
import com.example.loanmanagement.model.User;
import com.example.loanmanagement.repository.UserRepository;
import com.example.loanmanagement.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequestDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(User.Role.USER) // Ensure Role enum exists in User
                .build();
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestDto dto) {
        User user = userRepo.findByEmail(dto.getEmail()).orElseThrow();
        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            String token = jwtTokenUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}
