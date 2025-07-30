package bookstore.mapper;

import bookstore.domain.Book;
import bookstore.dto.BookDTO;

public class BookMapper {

    public static BookDTO toDTO(Book book) {
        return new BookDTO(book.getIsbn(), book.getAuthor(), book.getTitle(), book.getPrice());
    }

    public static Book toEntity(BookDTO dto) {
        return new Book(dto.getIsbn(), dto.getAuthor(), dto.getTitle(), dto.getPrice());
    }

}
