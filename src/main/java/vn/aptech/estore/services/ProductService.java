package vn.aptech.estore.services;

import vn.aptech.estore.entities.Product;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> findAll();

    Iterable<Product> findAllByOrderByCreatedDateDesc();

    Iterable<Product> findAllByOrderByUnitsOnOrderDesc();

    Iterable<Product> findByNameContaining(String name);

    Product save(Product product);

    Optional<Product> findById(Long id);
}
