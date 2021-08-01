package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.estore.entities.Brand;

@Repository
public interface BrandRepository extends CrudRepository<Brand, Long> {
}
