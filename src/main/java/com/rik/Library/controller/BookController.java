package com.rik.Library.controller;


import com.rik.Library.dto.Book.BookCopyUpdateRequest;
import com.rik.Library.dto.Book.BookRegisterRequest;
import com.rik.Library.entity.Book;
import com.rik.Library.services.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAll()
    {
        return this.bookService.getAll();
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody BookRegisterRequest book)
    {
        return this.bookService.addNew(book);
    }

    @GetMapping("/{id}")
    public Book findOneBookWithId(@PathVariable long id)
    {
        return this.bookService.getOne(id);
    }


    @PutMapping("/update/copies")
    public  Book updateBook(BookCopyUpdateRequest req)
    {
        return this.bookService.updateCopies(req);
    }

}
