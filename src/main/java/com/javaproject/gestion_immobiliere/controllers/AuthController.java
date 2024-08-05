package com.javaproject.gestion_immobiliere.controllers;

import com.javaproject.gestion_immobiliere.dto.AuthRequest;
import com.javaproject.gestion_immobiliere.dto.AuthResponse;
import com.javaproject.gestion_immobiliere.dto.RegisterRequest;
import com.javaproject.gestion_immobiliere.entities.User;
import com.javaproject.gestion_immobiliere.services.CustomUserDetailsService;
import com.javaproject.gestion_immobiliere.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(Map.of(), userDetails);

        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setBirthDate(registerRequest.getBirthDate());

        customUserDetailsService.saveUser(user);

        //UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        //String jwt = jwtUtils.generateToken(Map.of(), userDetails);

        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        if (token != null && jwtUtils.validateToken(token)) {
            return ResponseEntity.ok("Token is valid");
        } else {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}
