package com.rik.Library.dto.Author;

public record AuthorRegisterRequest(
        String name,
        String birthDate,
        String nationality
) {
}
