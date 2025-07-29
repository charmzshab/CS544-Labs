package bank.service;
import bank.domain.TraceRecord;
import bank.integration.EmailSender;
import bank.repositories.TransactionRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.domain.Account;
import bank.domain.Customer;
import bank.repositories.AccountRepository;
import bank.repositories.CustomerRepository;

@Service
public class BankService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private EmailSender emailSender;
	@Autowired
	private TraceService traceService;
	
	@Transactional
	public void createCustomerAndAccount(int customerId, String customerName, String emailAddress, String AccountNumber){


		String successMsg = "Customer "+customerName+" "+"created with account "+AccountNumber;
		String failMsg = "Could not create customer "+customerName+" "+"with account "+AccountNumber;
		try{
			Account account = new Account(AccountNumber);
			accountRepository.save(account);
			Customer customer = new Customer(customerId, customerName);
			customer.setAccount(account);
			customerRepository.saveCustomer(customer);
			emailSender.sendEmail(emailAddress, successMsg);

			traceService.record(successMsg);
		}catch(Exception ex){
			emailSender.sendEmail(emailAddress, failMsg);
			traceService.record(failMsg);
			throw ex;
		}


	}

}
