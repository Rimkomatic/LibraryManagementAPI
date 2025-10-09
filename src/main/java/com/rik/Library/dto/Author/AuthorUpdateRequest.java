package com.rik.Library.dto.Author;

public record AuthorUpdateRequest(
        String name,
        long id,
        String birthDate,
        String nationality

) {
}
