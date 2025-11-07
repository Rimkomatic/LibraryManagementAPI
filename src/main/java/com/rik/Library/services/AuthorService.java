package com.rik.Library.services;

import com.rik.Library.dto.Author.AuthorRegisterRequest;

import com.rik.Library.dto.Author.AuthorUpdateRequest;
import com.rik.Library.entity.Author;
import com.rik.Library.util.DateUtil;
import com.rik.Library.util.IdGenerator;
import org.springframework.stereotype.Service;

import com.rik.Library.repository.AuthorRepository;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public List<Author> getAuthors(){
        return this.repository.findAll();
    }

    public Author getAuthorById(Long id){
        return this.repository.findById(id).orElse(null);
    }

    public Author addAuthor(AuthorRegisterRequest author){

        Author newAuthor = new Author();



        long id;

        do {
            id = IdGenerator.generateSevenDigitId();
        } while (repository.existsById(id));

        newAuthor.setId(id);
        newAuthor.setName(author.name());
        newAuthor.setBirth_date(DateUtil.parseDate(author.birthDate()));
        newAuthor.setNationality(author.nationality());

        return repository.save(newAuthor);
    }

    public Author updateAuthor(AuthorUpdateRequest authorRequest) {
        // Step 1: Find the existing author
        Author existingAuthor = this.repository.findById(authorRequest.id())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + authorRequest.id()));


        existingAuthor.setName(authorRequest.name());
        existingAuthor.setNationality(authorRequest.nationality());
        existingAuthor.setBirth_date(DateUtil.parseDate(authorRequest.birthDate()));



        return this.repository.save(existingAuthor);
    }


}
