package com.marble.controllers;

import com.marble.config.JwtTokenProvider;
import com.marble.dto.AuthenticationRequest;
import com.marble.dto.AuthenticationResponse;
import com.marble.entities.Users;
import com.marble.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        try {
            // Authenticate mobile & password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getMobile(), request.getPassword())
            );

            Users user = userRepository.findByMobile(request.getMobile())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            List<String> roles = List.of(user.getRole().name());

            String jwt = jwtTokenProvider.generateToken(user.getMobile(), roles);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid mobile or password");
        }
    }
}
