package customers;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


public interface ICustomerDAO {
	void save(Customer customer) ;
}
