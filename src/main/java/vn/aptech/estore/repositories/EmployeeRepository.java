package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.estore.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByUsername(String username);
}
