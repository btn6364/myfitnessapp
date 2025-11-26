package com.baonguyen.fitnesstrackingapp.dto;

public record AuthenticationRequest(
        String email,
        String password
) {
}
