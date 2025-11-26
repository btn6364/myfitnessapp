package com.baonguyen.fitnesstrackingapp.controller;

import com.baonguyen.fitnesstrackingapp.dto.AuthenticationRequest;
import com.baonguyen.fitnesstrackingapp.dto.RegisterRequest;
import com.baonguyen.fitnesstrackingapp.service.AuthenticationService;
import com.baonguyen.fitnesstrackingapp.service.LogOutService;
import com.baonguyen.fitnesstrackingapp.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final RegisterService registerService;
    private final AuthenticationService authenticationService;
    private final LogOutService logOutService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return registerService.registerUser(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logOut() {
        return logOutService.logOut();
    }
}
