package vn.aptech.estore.services;

import vn.aptech.estore.entities.Customer;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/23/2021
 * Time: 10:29 AM
 */
public interface CustomerService {

    Iterable<Customer> findAll();

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);
}
