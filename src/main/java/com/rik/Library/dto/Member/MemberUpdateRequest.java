package com.rik.Library.dto.Member;

public record MemberUpdateRequest(
        long id,
        String name,
        String email,
        String password
) {
}
