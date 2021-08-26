package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.estore.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    Iterable<Product> findAllByOrderByCreatedDateDesc();

    Iterable<Product> findAllByOrderByUnitsOnOrderDesc();

    Iterable<Product> findByNameContaining(String name);

}
