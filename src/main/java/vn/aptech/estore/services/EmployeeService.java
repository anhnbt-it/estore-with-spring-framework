package vn.aptech.estore.services;

import vn.aptech.estore.entities.Employee;

import java.util.Optional;

public interface EmployeeService {
    Iterable<Employee> findAll();

    Employee save(Employee employee);

    Optional<Employee> findByUsername(String username);
}
