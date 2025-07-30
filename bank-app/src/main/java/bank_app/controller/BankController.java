package bank_app.controller;

import bank_app.domain.BankAccount;
import bank_app.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/create")
    public ResponseEntity<BankAccount> createAccount(@RequestParam String accountNumber,
                                                     @RequestParam String accountHolder,
                                                     @RequestParam double initialBalance) {
        return ResponseEntity.ok(bankService.createAccount(accountNumber, accountHolder, initialBalance));
    }

    @PostMapping("/deposit")
    public ResponseEntity<BankAccount> deposit(@RequestParam String accountNumber, @RequestParam double amount) {
        return ResponseEntity.ok(bankService.deposit(accountNumber, amount));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<BankAccount> withdraw(@RequestParam String accountNumber, @RequestParam double amount) {
        return ResponseEntity.ok(bankService.withdraw(accountNumber, amount));
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam String fromAccount,
                                           @RequestParam String toAccount,
                                           @RequestParam double amount) {
        bankService.transfer(fromAccount, toAccount, amount);
        return ResponseEntity.ok("Transfer successful.");
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable String accountNumber) {
        return ResponseEntity.ok(bankService.getAccount(accountNumber));
    }
}
