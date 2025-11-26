package com.baonguyen.fitnesstrackingapp.service;

import com.baonguyen.fitnesstrackingapp.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogOutService {

    private final JwtService jwtService;

    /**
     * Log out user by clearing JWT cookie
     * @return
     */
    public ResponseEntity<?> logOut() {
        ResponseCookie cookie = jwtService.generateEmptyJwtCookie();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Signed Out Successfully");
    }
}
