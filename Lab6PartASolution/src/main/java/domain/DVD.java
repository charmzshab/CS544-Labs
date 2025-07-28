package domain;

import jakarta.persistence.Entity;

@Entity
public class DVD extends Product{
    private String genre;
}
