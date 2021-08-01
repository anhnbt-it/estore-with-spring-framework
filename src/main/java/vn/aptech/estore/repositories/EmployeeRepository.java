package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import vn.aptech.estore.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
