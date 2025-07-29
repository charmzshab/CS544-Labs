package bank.service;

import bank.domain.TraceRecord;
import bank.repositories.TransactionRecordRepository;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

@Service
public class TraceService {
    @Autowired
    TransactionRecordRepository transactionRecordRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public void record(String message){
        transactionRecordRepository.save(new TraceRecord(message));
    }

}
