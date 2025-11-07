package com.rik.Library.dto.Member;

public record MemberRegistrationRequest(
        String name,
        String email,
        String password
) {
}
