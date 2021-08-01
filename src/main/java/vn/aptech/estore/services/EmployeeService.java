package vn.aptech.estore.services;

import vn.aptech.estore.entities.Employee;

public interface EmployeeService {
    Iterable<Employee> findAll();

    Employee save(Employee employee);
}
