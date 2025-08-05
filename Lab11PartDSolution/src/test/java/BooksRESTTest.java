import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import books.domain.Book;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;

public class BooksRESTTest {

    @BeforeClass
    public static void setup() {
        RestAssured.port = Integer.valueOf(8080);
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "";
    }

   @Test
    public void testGetOneBook() {
        // add the book to be fetched
        Book book = new Book("878","Book 123", 18.95, "Joe Smith");
        given()
                .contentType("application/json")
                .body(book)
                .when().post("/books").then()
                .statusCode(200);
        // test getting the book
        given()
                .when()
                .get("books/878")
                .then()
                .contentType(ContentType.JSON)
                .and()
                .body("isbn",equalTo("878"))
                .body("title",equalTo("Book 123"))
                .body("price",equalTo(18.95f))
                .body("author",equalTo("Joe Smith"));
        //cleanup
        given()
                .when()
                .delete("books/878");
    }

    @Test
    public void testAddBook() {
        Book book = new Book("111", "Spring Boot Basics", 25.99, "John Doe");

        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .body("isbn", equalTo("111"))
                .body("title", equalTo("Spring Boot Basics"))
                .body("author", equalTo("John Doe"))
                .body("price", equalTo(25.99f));

        // Cleanup
        delete("/books/111");
    }

    @Test
    public void testInspectBooksResponse() {
        when()
                .get("/books")
                .then()
                .statusCode(200)
                .log().body();  // Logs the full response body
    }

/*    {
        "books": [

    ]
    }*/


    @Test
    public void testGetAllBooks() {
        Book book = new Book("222", "REST with Spring", 19.99, "Jane Smith");

        // Add the book first
        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when().post("/books")
                .then().statusCode(200);

        // Assert that the book appears in the wrapped response
        given()
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("books.find { it.isbn == '222' }.isbn", equalTo("222"))
                .body("books.find { it.isbn == '222' }.title", equalTo("REST with Spring"))
                .body("books.find { it.isbn == '222' }.price", equalTo(19.99f))
                .body("books.find { it.isbn == '222' }.author", equalTo("Jane Smith"));

        // Cleanup
        delete("/books/222");
    }

    @Test
    public void testUpdateBook() {
        Book book = new Book("333", "Old Title", 10.00, "Old Author");
        given().contentType(ContentType.JSON).body(book).post("/books");

        Book updatedBook = new Book("333", "Updated Title", 15.50, "New Author");
        given()
                .contentType(ContentType.JSON)
                .body(updatedBook)
                .when()
                .put("/books/333")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .body("price", equalTo(15.50f))
                .body("author", equalTo("New Author"));

        // Cleanup
        delete("/books/333");
    }

    @Test
    public void testDeleteBook() {
        Book book = new Book("444", "Delete Me", 12.00, "Delete Author");

        // Add the book
        given()
                .contentType(ContentType.JSON)
                .body(book)
                .when()
                .post("/books")
                .then()
                .statusCode(anyOf(is(200), is(201))); // Accept 200 or 201

        // Delete the book
        when()
                .delete("/books/444")
                .then()
                .statusCode(204);  // Expect 204, which is standard for DELETE

        // Confirm deletion
        when()
                .get("/books/444")
                .then()
                .statusCode(404);
    }

}
