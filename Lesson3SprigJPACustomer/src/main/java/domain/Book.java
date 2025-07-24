package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String title;

    public Book() {
    }

    public Book(String title, String ISBN, String author, double price) {
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }

    private String ISBN;
    private String author;
    private double price;

    public long getId(){ return id;}
}
