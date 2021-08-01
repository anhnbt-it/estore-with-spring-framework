package vn.aptech.estore.services;

import vn.aptech.estore.entities.Category;

import java.util.Optional;

public interface CategoryService {
    Iterable<Category> findAll();

    Category save(Category category);

    Optional<Category> findById(Long id);
}
