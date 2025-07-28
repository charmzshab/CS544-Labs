package domain;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@NamedQuery(
        name = "CD.findByArtist",
        query = "SELECT c FROM CD c WHERE c.artist = :artist"
)
@Entity
public class CD extends Product {
    private String artist;
}
