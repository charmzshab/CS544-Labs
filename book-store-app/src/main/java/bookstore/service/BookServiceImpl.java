package bookstore.service;

import bookstore.domain.Book;
import bookstore.dto.BookDTO;
import bookstore.exception.DuplicateIsbnException;
import bookstore.mapper.BookMapper;
import bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepo;

    public BookDTO addBook(BookDTO bookDTO) {
        if (bookRepo.existsByIsbn(bookDTO.getIsbn())) {
            throw new DuplicateIsbnException(bookDTO.getIsbn());
        }
        Book book = BookMapper.toEntity(bookDTO);
        return BookMapper.toDTO(bookRepo.save(book));
    }

    public BookDTO updateBook(BookDTO bookDTO) {
        Book existing = bookRepo.findByIsbn(bookDTO.getIsbn())
                .orElseThrow(() -> new RuntimeException("Book not found"));
        existing.setAuthor(bookDTO.getAuthor());
        existing.setTitle(bookDTO.getTitle());
        existing.setPrice(bookDTO.getPrice());
        return BookMapper.toDTO(bookRepo.save(existing));
    }

    public void deleteBook(String isbn) {
        if (!bookRepo.existsByIsbn(isbn)) {
            throw new RuntimeException("Book not found");
        }
        bookRepo.deleteByIsbn(isbn);
    }

    public BookDTO getBook(String isbn) {
        return bookRepo.findByIsbn(isbn)
                .map(BookMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public List<BookDTO> getAllBooks() {
        return bookRepo.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> searchBooks(String author) {
        return bookRepo.findByAuthorContainingIgnoreCase(author)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
