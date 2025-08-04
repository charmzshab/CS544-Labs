package bank.service;

import bank.dao.IAccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BankStatementPrinter {
    @Autowired
    private IAccountDAO accountDAO;

    @Scheduled(fixedRate = 10000) // every 10 seconds
    public void printStatements(){
        System.out.println("---- Bank Statements ----");
        accountDAO.findAll().forEach(a ->
                System.out.println(a.getAccountnumber() + " : " + a.getBalance())
        );
    }
}
