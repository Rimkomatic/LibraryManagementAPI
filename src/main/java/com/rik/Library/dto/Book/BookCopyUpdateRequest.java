package com.rik.Library.dto.Book;

public record BookCopyUpdateRequest(
        long id,
        int copies
) {
}
