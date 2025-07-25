package app;

import java.util.*;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.*;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class DepartmentApplication implements CommandLineRunner{

	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	PublisherRepository publisherRepository;
	@Autowired
	BookRepository bookRepository;
	@Autowired
	PassengerRepository passengerRepository;
	@Autowired
	FlightRepository flightRepository;
	@Autowired
	SchoolRepository schoolRepository;
	@Autowired
	StudentRepository studentRepository;


	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Department dept = new Department();
		dept.setName("IT");

		Employee emp1 = new Employee();
		emp1.setName("Alice");

		Employee emp2 = new Employee();
		emp2.setName("Bob");

// Set bidirectional link
		emp1.setDepartment(dept);
		emp2.setDepartment(dept);

		List<Employee> employees = new ArrayList<>();
		employees.add(emp1);
		employees.add(emp2);

		dept.setEmployees(employees);

		departmentRepository.save(dept);


		Optional<Department> deptFromDb = departmentRepository.findById(dept.getId());

		if (deptFromDb.isPresent()) {
			Department retrieved = deptFromDb.get();
			List<Employee> employeesList = retrieved.getEmployees(); // will be loaded if fetch = EAGER
			employeesList.forEach(e -> System.out.println(e.getName()));
		}

		Publisher publisher = new Publisher("shaban");
		publisherRepository.save(publisher);

		Book book1 = new Book("Wizard of Oz","Shaban");
		book1.setPublisher(publisher);
		Book savedBook= bookRepository.save(book1);
		System.out.println(savedBook);



		Passenger passenger = new Passenger("Shaban");
		List<Flight> flights = new ArrayList<>();

		Flight flight1 = new Flight("EZ2345","Chicago","Florida");
		Flight flight2 = new Flight("EZ2389","Boston","New York");

		flights.add(flight1);
		flights.add(flight2);

		passenger.setFlights(flights);

		passengerRepository.save(passenger);

		Optional<Passenger> passengerFromDb = passengerRepository.findById(passenger.getId());

		if (passengerFromDb.isPresent()) {
			Passenger retrieved = passengerFromDb.get();
			List<Flight> flightList = retrieved.getFlights(); // will be loaded if fetch = EAGER
			flightList.forEach(System.out::println);
		}

		School school = new School("Kibuli");
		schoolRepository.save(school);

		Map<Integer,Student> studentMap = new HashMap<>();
		Student std1 = new  Student("Raldin","Hidalgo");
		Student std2 = new  Student("Shaban","Hidalgo");


		studentMap.put(std1.getId(),std1);
		studentMap.put(std2.getId(),std2);

		school.setStudentMap(studentMap);
		schoolRepository.save(school);













	}
}
