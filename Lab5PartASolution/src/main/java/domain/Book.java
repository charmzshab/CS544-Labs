package domain;

import jakarta.persistence.Entity;

@Entity
public class Book extends Product {
    private String isbn;
}
