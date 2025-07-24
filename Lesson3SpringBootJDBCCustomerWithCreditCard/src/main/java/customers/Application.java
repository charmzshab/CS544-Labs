package customers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private ProductDAO productDAO;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerDao.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerDao.save(customer);
		System.out.println(customerDao.getCustomer(101));
		System.out.println(customerDao.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerDao.getAllCustomers());

		productDAO.clearDB();
		Product product = new Product(10,"Electric Kettle",100.00);
		Supplier supplier = new Supplier("Huawei", "64212228");
		product.setSupplier(supplier);
		productDAO.save(product);
		product = new Product(209,"Electric bike",2550.0);
		supplier = new Supplier("Escooters", "2134567895");
		product.setSupplier(supplier);
		productDAO.save(product);
		product = new Product(208,"EWatch",1200.00);
		supplier = new Supplier("Apple", "2114568795");
		product.setSupplier(supplier);
		productDAO.save(product);

		System.out.println("-------Find Product by Number-------------");
		System.out.println(productDAO.findByProductNumber(10));
		System.out.println(productDAO.findByProductNumber(209));

		System.out.println("-------Find by Name-----------------------");
		System.out.println(productDAO.findByProductName("Electric Kettle"));
		System.out.println(productDAO.findByProductName("Electric bike"));

		System.out.println("--------Get all Products-------------------");
		System.out.println(productDAO.getAllProducts());

		System.out.println("--------Remove Products-------------------");
		productDAO.removeProduct(208);
		productDAO.removeProduct(209);

		System.out.println("--------Get all Products not Deleted---------------");
		System.out.println(productDAO.getAllProducts());
	}
}
