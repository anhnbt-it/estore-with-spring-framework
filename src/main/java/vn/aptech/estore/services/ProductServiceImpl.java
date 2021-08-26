package vn.aptech.estore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.repositories.ProductRepository;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> findAllByOrderByCreatedDateDesc() {
        return productRepository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public Iterable<Product> findAllByOrderByUnitsOnOrderDesc() {
        return productRepository.findAllByOrderByUnitsOnOrderDesc();
    }

    @Override
    public Iterable<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Transactional(rollbackFor = {SQLException.class})
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }
}
