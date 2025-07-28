package domain.specifications;

import domain.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecification {
    public static Specification<Customer> livesInAmsterdam() {
            return (root, query, cb) -> cb.equal(root.get("address").get("city"), "Amsterdam");
        }
}
