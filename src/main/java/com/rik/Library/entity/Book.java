package com.rik.Library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(unique = true, nullable = false, length = 13)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private LocalDate published_date;

    @Column(nullable = false)
    private int available_copies = 0;

    @Column(nullable = false)
    private int total_copies;
}
