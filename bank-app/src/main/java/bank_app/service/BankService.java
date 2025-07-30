package bank_app.service;

import bank_app.domain.BankAccount;
import bank_app.exception.InsufficientFundsException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BankService {

    private final Map<String, BankAccount> accounts = new HashMap<>();

    public BankAccount createAccount(String accountNumber, String accountHolder, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, accountHolder, initialBalance);
        accounts.put(accountNumber, account);
        return account;
    }

    public BankAccount deposit(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);
        return account;
    }

    public BankAccount withdraw(String accountNumber, double amount) {
        BankAccount account = getAccount(accountNumber);
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        account.setBalance(account.getBalance() - amount);
        return account;
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        BankAccount source = getAccount(fromAccount);
        BankAccount destination = getAccount(toAccount);

        if (source.getBalance() < amount) {
            throw new InsufficientFundsException("Insufficient funds for transfer.");
        }
        source.setBalance(source.getBalance() - amount);
        destination.setBalance(destination.getBalance() + amount);
    }

    public BankAccount getAccount(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            throw new RuntimeException("Account not found.");
        }
        return accounts.get(accountNumber);
    }
}
