package domain;


import jakarta.persistence.*;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private long id;
	private String firstName;
	private String lastName;
	private String email;

	protected Customer() {
	}

	public Customer(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}



	@Override
	public String toString() {
		return String.format("Customer[id=%d, firstName='%s', lastName='%s', lastName='%s']", id, firstName, lastName, email);
	}

}


