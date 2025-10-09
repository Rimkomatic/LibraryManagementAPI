package com.rik.Library.controller;

import com.rik.Library.dto.Author.AuthorRegisterRequest;
import com.rik.Library.dto.Author.AuthorRegisterResponse;
import com.rik.Library.dto.Author.AuthorUpdateRequest;
import com.rik.Library.entity.Author;
import com.rik.Library.repository.AuthorRepository;
import com.rik.Library.services.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    private AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public List<Author> getAllAuthors() {
        return this.authorService.getAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable Long id) {
        return this.authorService.getAuthorById(id);
    }

    @PostMapping("/register")
    public Author registerAuthor(@RequestBody AuthorRegisterRequest author){
        return this.authorService.addAuthor(author);
    }

    @PostMapping("/update")
    public Author updateAuthor(@RequestBody AuthorUpdateRequest author){
        return this.authorService.updateAuthor(author);
    }

}
