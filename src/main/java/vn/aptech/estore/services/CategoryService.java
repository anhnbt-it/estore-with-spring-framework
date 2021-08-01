package vn.aptech.estore.services;

import vn.aptech.estore.entities.Category;

public interface CategoryService {
    Iterable<Category> findAll();

    Category save(Category category);
}
