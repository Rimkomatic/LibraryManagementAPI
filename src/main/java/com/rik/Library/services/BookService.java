package com.rik.Library.services;

import com.rik.Library.dto.Book.BookCopyUpdateRequest;
import com.rik.Library.dto.Book.BookRegisterRequest;
import com.rik.Library.entity.Author;
import com.rik.Library.entity.Book;
import com.rik.Library.repository.AuthorRepository;
import com.rik.Library.repository.BookRepository;
import com.rik.Library.util.DateUtil;
import com.rik.Library.util.IdGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository , AuthorRepository authorRepository)
    {
        this.repository = bookRepository;
        this.authorRepository=authorRepository;
    }


    public Book addNew(BookRegisterRequest book)
    {
        Book newBook = new Book();

        long id ;

        do {
            id = IdGenerator.generateSevenDigitId();
        }while (this.repository.existsById(id));

        newBook.setId(id);
        newBook.setTitle(book.title());
        newBook.setIsbn(book.isbn());

        Author author = authorRepository.findById(book.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        newBook.setAuthor(author);


        newBook.setPublished_date(DateUtil.parseDate(book.publishedDate()));

        newBook.setTotal_copies(book.totalCopies());
        newBook.setAvailable_copies(newBook.getTotal_copies());

        return repository.save(newBook);
    }

    public List<Book> getAll()
    {
        return repository.findAll();
    }

    public Book getOne(long id)
    {
        return repository.findById(id)
                .orElseThrow( () -> new RuntimeException("Book Not Found") );
    }

    public Book updateCopies(BookCopyUpdateRequest updatingBook){
        Book book = repository.findById(updatingBook.id())
                .orElseThrow( () -> new RuntimeException("Book Not Found") );

        book.setTotal_copies(updatingBook.copies());

        return repository.save(book);
    }

}
