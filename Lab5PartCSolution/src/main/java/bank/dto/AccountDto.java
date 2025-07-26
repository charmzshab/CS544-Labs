package bank.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


public class AccountDto {
	long accountnumber;
	Collection<AccountEntryDto> entryList = new ArrayList<AccountEntryDto>();
	CustomerDto customer;

	
	public AccountDto(long accountnr){
		this.accountnumber = accountnr;
	}
	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public double getBalance() {
		double balance=0;
		for (AccountEntryDto entry : entryList) {
			balance+=entry.getAmount();
		}
		return balance;
	}
	public void deposit(double amount){
		AccountEntryDto entry = new AccountEntryDto(new Date(), amount, "deposit", "", "");
		entryList.add(entry);
	}
	public void withdraw(double amount){
		AccountEntryDto entry = new AccountEntryDto(new Date(), -amount, "withdraw", "", "");
		entryList.add(entry);	
	}

	private void addEntry(AccountEntryDto entry){
		entryList.add(entry);
	}

	public void transferFunds(AccountDto toAccount, double amount, String description){
		AccountEntryDto fromEntry = new AccountEntryDto(new Date(), -amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
		AccountEntryDto toEntry = new AccountEntryDto(new Date(), amount, description, ""+toAccount.getAccountnumber(), toAccount.getCustomer().getName());
		entryList.add(fromEntry);	
		toAccount.addEntry(toEntry);
	}
	
	public CustomerDto getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}
	public Collection<AccountEntryDto> getEntryList() {
		return entryList;
	}

}
