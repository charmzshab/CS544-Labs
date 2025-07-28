package domain.specifications;

import domain.CD;
import org.springframework.data.jpa.domain.Specification;

public class CDSpecification {
    public static Specification<CD> fromArtistWithMinPrice(String artist, double price) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get("artist"), artist),
                cb.greaterThan(root.get("price"), price)
        );
    }
}
