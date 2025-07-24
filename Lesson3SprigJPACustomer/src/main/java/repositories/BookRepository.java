package repositories;

import domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query("UPDATE Book b SET b.price = :price WHERE b.id = :id")
    @Transactional
    void updateBookPrice(@Param("id") Long id, @Param("price") double price);

    @Modifying
    @Query("DELETE FROM Book b WHERE b.id = :id")
    @Transactional
    void deleteBook(@Param("id") Long id);
}
