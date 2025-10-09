package com.rik.Library.dto.Author;

public record AuthorRegisterResponse(
        long id,
        String name,
        String birth_date,
        String nationality
) {
}
