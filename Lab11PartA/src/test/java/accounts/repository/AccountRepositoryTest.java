package accounts.repository;

import accounts.domain.Account;
import accounts.repositories.AccountRepository;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {

    }
    static void tearDownAfterClass() throws Exception {
    }
    @Test
    @DisplayName("Should return account for given account holder name")
    public void testFindByAccountHolder() {
        Account acc1 = new Account("123456", 1500.0, "John Doe");
        accountRepository.save(acc1);

        // Act: find account by account holder
        Account johnsAccount = accountRepository.findByAccountHolder("John Doe");

        assertThat(johnsAccount).isNotNull();
        assertThat(johnsAccount.getAccountHolder()).isEqualTo("John Doe");
        assertThat(johnsAccount.getAccountNumber()).isEqualTo("123456");
        assertThat(johnsAccount.getBalance()).isEqualTo(1500.0);
    }
}
