package vn.aptech.estore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.exception.CommonException;
import vn.aptech.estore.repositories.CategoryRepository;
import vn.aptech.estore.validate.ValidateCommon;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        ValidateCommon.validateNullObject(category.getName());
        if (ValidateCommon.isValidStringLength(category.getName(), 5, 160)) {
            throw new CommonException("Độ dài không hợp lệ (Tối thiểu 5 ký tự và tối đa 160 ký tự)");
        }
        if (ValidateCommon.isValidCharacter(category.getName())) {
            throw new CommonException("Trường không được chứa ký tự đặc biệt (Chỉ bao gồm chữ cái và chữ số)");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }
}
