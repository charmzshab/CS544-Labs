package bank.domain;

import org.junit.jupiter.api.Test;
import bank.domain.Account;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;

import bank.domain.Account;
import bank.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

    public class AccountTest {

        private Account account;
        private Customer customer;

        @BeforeEach
        public void setup() {
            account = new Account(123456);
            customer = new Customer();
            customer.setName("John Doe");
            account.setCustomer(customer);
        }
        @Test
        public void testIncrement(){
            account.deposit(100.0);
            assertThat( account.getBalance(), closeTo (100.0, 0.01));
        }

        @Test
        public void testDecrement(){
            account.deposit(100.0);
            account.withdraw(50.0);
            assertThat(account.getBalance(), closeTo(50.0, 0.01));
        }

        @Test
        public void testTransfer(){
            account.deposit(300);
            Account toAccount = new Account();
            toAccount.setCustomer(new Customer("Jane Smith"));
            account.transferFunds(toAccount,100.0, "Pocket Money");
            assertThat(account.getBalance(), closeTo(200.0, 0.01));
            assertThat(toAccount.getBalance(), closeTo(100.0,0.01));
        }

        @Test
        public void testDepositWithNegativeAmountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
        }

        @Test
        public void testWithdrawWithNegativeAmountThrowsException() {
            assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50));
        }

        @Test
        public void testTransferWithNegativeAmountThrowsException() {
            Account target = new Account(999999);
            target.setCustomer(new Customer("Jane Doe"));
            assertThrows(IllegalArgumentException.class, () -> account.transferFunds(target, -200, "Invalid transfer"));
        }

        @Test
        public void testWithdrawMoreThanBalanceThrowsException() {
            account.deposit(100.0);
            assertThrows(IllegalStateException.class, () -> account.withdraw(200.0));
        }

        @Test
        public void testTransferMoreThanBalanceThrowsException() {
            Account toAccount = new Account(222222);
            Customer toCustomer = new Customer();
            toCustomer.setName("Alice");
            toAccount.setCustomer(toCustomer);

            account.deposit(100.0);
            assertThrows(IllegalStateException.class, () -> account.transferFunds(toAccount, 150.0, "Attempted overdraft"));
        }

        }


