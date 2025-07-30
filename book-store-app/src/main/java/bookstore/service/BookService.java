package bookstore.service;

import bookstore.domain.Book;
import bookstore.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO);
    void deleteBook(String isbn);
    BookDTO getBook(String isbn);
    List<BookDTO> getAllBooks();
    List<BookDTO> searchBooks(String author);
}
