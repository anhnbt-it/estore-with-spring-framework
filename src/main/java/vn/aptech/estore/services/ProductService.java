package vn.aptech.estore.services;

import vn.aptech.estore.entities.Product;

public interface ProductService {
    Iterable<Product> findAll();

    Product save(Product product);
}
