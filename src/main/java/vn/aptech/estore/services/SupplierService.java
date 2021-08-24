package vn.aptech.estore.services;

import vn.aptech.estore.entities.Supplier;

import java.util.Optional;

public interface SupplierService {
    Iterable<Supplier> findAll();

    Supplier save(Supplier supplier);

    void deleteById(Long id);

    Optional<Supplier> findById(Long id);
}
