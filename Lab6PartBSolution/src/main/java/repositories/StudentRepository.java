package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByDepartmentName(String departmentName);

    List<Student> findDistinctByGradesCourseName(String courseName);

    @Query("SELECT s FROM Student s WHERE s.department.name = :deptName")
    List<Student> findByDepartment(@Param("deptName") String deptName);

    @Query("SELECT DISTINCT s FROM Student s JOIN s.grades g WHERE g.course.name = :courseName")
    List<Student> findByCourseName(@Param("courseName") String courseName);
}
