package repositories;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> ,JpaSpecificationExecutor<Customer> {
    List<Customer> findAll();

    List<Customer> findCustomerByAddressZip(String addressZip);

    @Query("SELECT DISTINCT o.customer FROM Order o JOIN o.orderlines ol JOIN ol.product p " +
            "WHERE TYPE(p) = DVD AND p.name = :dvdName")
    List<Customer> findCustomersWhoOrderedDvdByName(@Param("dvdName") String dvdName);

    @Query(name = "Customer.findAllFromUSA")
    List<Customer> findAllFromUSA();

    @Query("SELECT c.firstname, c.lastname FROM Customer c WHERE c.address.city = 'Amsterdam'")
    List<Object[]> findCustomersInAmsterdam();


}
