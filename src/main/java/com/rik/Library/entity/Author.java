package com.rik.Library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.rik.Library.util.IdGenerator;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @Column(unique = true, nullable = false)
    private Long id;

    private String name;
    private LocalDate birth_date;
    private String nationality;

}
