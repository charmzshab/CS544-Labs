package Lab14PartC;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfitRepository extends JpaRepository<Profit, Long> {
    Optional<Profit> findByMonth(String month);
}
