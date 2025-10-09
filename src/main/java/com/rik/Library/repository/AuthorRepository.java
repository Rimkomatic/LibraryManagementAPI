package com.rik.Library.repository;

import com.rik.Library.dto.Author.AuthorRegisterRequest;
import com.rik.Library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long>{
}
