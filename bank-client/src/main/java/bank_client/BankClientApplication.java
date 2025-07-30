package bank_client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BankClientApplication implements CommandLineRunner {

	private final RestTemplate restTemplate = new RestTemplate();
	private final String BASE_URL = "http://localhost:8080/bank";

	public static void main(String[] args) {
		SpringApplication.run(BankClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		createAccount("123", "Alice", 1000);
		createAccount("456", "Bob", 500);

		deposit("123", 200);
		withdraw("123", 300);

		transfer("123", "456", 400);

		getAccount("123");
		getAccount("456");
	}
	private void createAccount(String accNo, String holder, double balance) {
		String url = BASE_URL + "/create?accountNumber=" + accNo + "&accountHolder=" + holder + "&initialBalance=" + balance;
		ResponseEntity<BankAccountDTO> response = restTemplate.postForEntity(url, null, BankAccountDTO.class);
		System.out.println("Account created: " + response.getBody());
	}

	private void deposit(String accNo, double amount) {
		String url = BASE_URL + "/deposit?accountNumber=" + accNo + "&amount=" + amount;
		ResponseEntity<BankAccountDTO> response = restTemplate.postForEntity(url, null, BankAccountDTO.class);
		System.out.println("Deposited: " + response.getBody());
	}

	private void withdraw(String accNo, double amount) {
		String url = BASE_URL + "/withdraw?accountNumber=" + accNo + "&amount=" + amount;
		try {
			ResponseEntity<BankAccountDTO> response = restTemplate.postForEntity(url, null, BankAccountDTO.class);
			System.out.println("Withdrawn: " + response.getBody());
		} catch (Exception ex) {
			System.out.println("Withdraw error: " + ex.getMessage());
		}
	}

	private void transfer(String from, String to, double amount) {
		String url = BASE_URL + "/transfer?fromAccount=" + from + "&toAccount=" + to + "&amount=" + amount;
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
			System.out.println("Transfer response: " + response.getBody());
		} catch (Exception ex) {
			System.out.println("Transfer error: " + ex.getMessage());
		}
	}

	private void getAccount(String accNo) {
		String url = BASE_URL + "/account/" + accNo;
		try {
			ResponseEntity<BankAccountDTO> response = restTemplate.getForEntity(url, BankAccountDTO.class);
			System.out.println("Account info: " + response.getBody());
		} catch (Exception ex) {
			System.out.println("Get account error: " + ex.getMessage());
		}
	}


}
