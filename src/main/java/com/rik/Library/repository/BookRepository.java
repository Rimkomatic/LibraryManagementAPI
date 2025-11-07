package com.rik.Library.repository;


import com.rik.Library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book , Long> {

//@Query("UPDATE Loan l SET l.status = 'OVERDUE' WHERE l.status = 'BORROWED' AND l.loan_date <= :cutoffDate")

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Book b SET b.available_copies =b.available_copies-1  WHERE b.id=:id AND b.available_copies > 0")
    void decreseAvialableCopies(@Param("id") long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Book b SET b.available_copies =b.available_copies+1  WHERE b.id=:id AND b.available_copies < b.total_copies")
    void increaseAvialableCopies(@Param("id") long id);

}
