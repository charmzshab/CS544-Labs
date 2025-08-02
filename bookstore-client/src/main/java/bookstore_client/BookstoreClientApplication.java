package bookstore_client;

import bookstore_client.dto.BookDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class BookstoreClientApplication implements CommandLineRunner {

	private final RestClient restTemplate = new RestClient();
	private final String BASE_URL = "http://localhost:8080/books";

	RestClient restClient = RestClient.builder().baseUrl("http://localhost:8080")             .build();

	public static void main(String[] args) {
		SpringApplication.run(BookstoreClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Add book
		BookDTO bookResponse = restClient.post().uri("/books").contentType(MediaType.APPLICATION_JSON).body(new BookDTO("1234567890", "Client Author", "Client Title", 39.99)).retrieve().body(BookDTO.class);

		// Get all books
		ResponseEntity<BookDTO[]> allBooks = restTemplate.getForEntity(BASE_URL, BookDTO[].class);
		List<BookDTO> bookList = Arrays.asList(allBooks.getBody());
		bookList.forEach(book -> System.out.println("Book: " + book.getTitle()));

		@RequestMapping(value="/books", method= RequestMethod.GET)
		public ResponseEntity<?> getAllBooks() {
			return new ResponseEntity<Collection<BookDTO>>(books.values(), HttpStatus.OK);
		}
		List<BookDTO> books = restClient.get() .uri("/books") .retrieve() .body(new ParameterizedTypeReference<List<BookDTO>>() {}); System.out.println(books);

		// Update book
		newBook.setPrice(29.99);
		HttpEntity<BookDTO> updateEntity = new HttpEntity<>(newBook);
		ResponseEntity<BookDTO> updated = restTemplate.exchange(BASE_URL, HttpMethod.PUT, updateEntity, BookDTO.class);
		System.out.println("Updated: " + updated.getBody());

		// Get single book
		ResponseEntity<BookDTO> single = restTemplate.getForEntity(BASE_URL + "/1234567890", BookDTO.class);
		System.out.println("Fetched book: " + single.getBody());

		// Delete book
		restTemplate.delete(BASE_URL + "/1234567890");
		System.out.println("Book deleted.");
	}
}
