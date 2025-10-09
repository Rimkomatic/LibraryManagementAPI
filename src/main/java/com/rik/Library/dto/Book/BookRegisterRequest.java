package com.rik.Library.dto.Book;

public record BookRegisterRequest(
        String title,
        String isbn,
        Long authorId,
        String publishedDate,
        int totalCopies
) {
}
