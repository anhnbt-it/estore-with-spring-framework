package vn.aptech.estore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.estore.entities.Brand;
import vn.aptech.estore.repositories.BrandRepository;

import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public Iterable<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id);
    }
}
