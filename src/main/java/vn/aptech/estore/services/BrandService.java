package vn.aptech.estore.services;

import vn.aptech.estore.entities.Brand;

public interface BrandService {
    Iterable<Brand> findAll();

    Brand save(Brand brand);
}
