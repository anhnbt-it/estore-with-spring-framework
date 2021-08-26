package vn.aptech.estore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.aptech.estore.entities.AttributeGroup;
import vn.aptech.estore.repositories.AttributeGroupRepository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/26/2021
 * Time: 7:26 AM
 */
@Service
@Transactional
public class AttributeGroupServiceImpl implements AttributeGroupService {

    @Autowired
    private AttributeGroupRepository attributeGroupRepository;

    @Override
    public Iterable<AttributeGroup> findAll() {
        return attributeGroupRepository.findAll();
    }

    @Override
    public AttributeGroup save(AttributeGroup attributeGroup) {
        return attributeGroupRepository.save(attributeGroup);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Optional<AttributeGroup> findById(Long id) {
        return attributeGroupRepository.findById(id);
    }
}
