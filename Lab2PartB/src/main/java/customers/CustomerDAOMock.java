package customers;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("test")
public class CustomerDAOMock implements ICustomerDAO {
    private List<Customer> savedCustomers = new ArrayList<>();

    @Override
    public void save(Customer customer) {
        System.out.println("MockCustomerDAO: pretending to save customer " + customer.getName());
        savedCustomers.add(customer);  // Simulate saving
    }

    public List<Customer> getSavedCustomers() {
        return savedCustomers;
    }
}

