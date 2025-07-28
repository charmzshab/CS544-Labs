package app;


import domain.Account;
import domain.Customer;
import domain.CustomerNameView;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import repositories.CustomerRepository;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        insertCustomers();
        retrieveCustomers();
        updateCustomers();
    }

    //Non-optimized code
//	private void insertCustomers() {
//		for (int x=0; x<50000; x++) {
//			Customer customer = new Customer("John Doe "+x);
//			Account account = new Account("123"+x);
//			customer.setAccount(account);
//			customerRepository.save(customer);
//			System.out.println("Inserting customer  "+x);
//		}
//	}

    //Optimized code
    private void insertCustomers() {
        List<Customer> batch = new ArrayList<>();
        for (int x = 0; x < 50000; x++) {
            Customer customer = new Customer("John Doe " + x);
            customer.setAccount(new Account("123" + x));
            batch.add(customer);

            if (x % 50 == 0) {
                customerRepository.saveAll(batch);
                batch.clear();
            }
        }
        if (!batch.isEmpty()) customerRepository.saveAll(batch);
    }

    //Non-optimized code
//    private void retrieveCustomers() {
//        System.out.println("Retrieving all customers ...");
//        long start = System.currentTimeMillis();
//
//        List<Customer> customers = customerRepository.findAll();
//        long finish = System.currentTimeMillis();
//        long timeElapsed = finish - start;
//        System.out.println("To retrieve all Customers took " + timeElapsed + " ms");
//    }

    //Optimized code
    private void retrieveCustomers() {
        System.out.println("Retrieving all customer names ...");
        long start = System.currentTimeMillis();

        List<CustomerNameView> customers = customerRepository.findAllProjectedBy();

        long finish = System.currentTimeMillis();
        System.out.println("To retrieve all Customers took " + (finish - start) + " ms");
    }

    //Non-optimized code
    //	private void updateCustomers() {
//		System.out.println("Change the name of all customers ...");
//		long start = System.currentTimeMillis();
//
//		List<Customer> customers = customerRepository.findAll();
//		for(Customer c: customers){
//			c.setName("James Bond");
//			customerRepository.save(c);
//		}
//		long finish = System.currentTimeMillis();
//		long timeElapsed = finish - start;
//		System.out.println("To change the name of all customers took "+timeElapsed+" ms");
//	}

    //Optimized code
    @Transactional
    protected void updateCustomers() {
        System.out.println("Change the name of all customers.");
        long start = System.currentTimeMillis();

        customerRepository.updateAllNames("James Bond");

        long finish = System.currentTimeMillis();
        System.out.println("To change the name of all customers took " + (finish - start) + " ms");
    }


}
