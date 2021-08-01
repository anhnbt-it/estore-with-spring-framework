package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.estore.entities.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Long> {
}
