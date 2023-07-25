package uz.pdp.bankamat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.bankamat.entity.Employee;

import javax.swing.text.html.Option;
import javax.validation.constraints.Email;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    boolean existsByEmail(@Email String email);
Optional<Employee> findByEmail( String email);

    void deleteByEmail(@Email String email);
}
