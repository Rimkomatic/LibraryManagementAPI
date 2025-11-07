package com.rik.Library.dto.Auth;

public record AuthRequest(
        String email,
        String password
) {
}
