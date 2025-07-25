package domain;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;
@Entity
public class Employee {
    private int employeeNumber;
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "employee_number", unique = true, nullable = false)
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Column(name = "employee_name", nullable = false, length = 30)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    private Department department;

    @ManyToOne
    @JoinColumn(name = "department_id")
    public Department getDepartment() {
        return department;
    }
}
