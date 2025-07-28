package repositories;

import domain.Customer;
import domain.CustomerNameView;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Modifying
    @Query("UPDATE Customer c SET c.name = :newName")
    void updateAllNames(@Param("newName") String newName);

    List<CustomerNameView> findAllProjectedBy();

}




