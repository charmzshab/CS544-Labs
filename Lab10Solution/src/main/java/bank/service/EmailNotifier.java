package bank.service;

import bank.domain.AccountChangedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifier {
    @EventListener
    public void onAccountChange(AccountChangedEvent event) {
        System.out.println("Email: Account " + event.getAccountNumber() +
                " had a " + event.getOperation() +
                " of amount " + event.getAmount());
    }
}
