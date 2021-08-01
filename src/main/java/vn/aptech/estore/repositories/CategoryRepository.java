package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.estore.entities.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
