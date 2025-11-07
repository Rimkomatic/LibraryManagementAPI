package com.rik.Library.dto.Auth;

public record AuthResponse(
        String email,
        long id,
        String name
) {
}
