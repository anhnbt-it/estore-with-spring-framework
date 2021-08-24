package vn.aptech.estore.services;

import vn.aptech.estore.entities.Brand;

import java.util.Optional;

public interface BrandService {
    Iterable<Brand> findAll();

    Brand save(Brand category);

    void deleteById(Long id);

    Optional<Brand> findById(Long id);
}
