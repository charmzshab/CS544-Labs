package bank.service;

import bank.dao.TraceRecordRepository;
import bank.domain.AccountChangedEvent;
import bank.domain.TraceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TraceRecordWriter {

    @Autowired
    TraceRecordRepository repo;

    @EventListener
    public void onAccountChange(AccountChangedEvent event){
        TraceRecord record = new TraceRecord();
        record.setDateTime(LocalDateTime.now());
        record.setAccountNumber(event.getAccountNumber());
        record.setOperation(event.getOperation());
        record.setAmount(event.getAmount());
        repo.save(record);
    }
}
