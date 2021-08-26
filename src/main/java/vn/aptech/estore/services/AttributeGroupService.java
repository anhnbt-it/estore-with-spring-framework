package vn.aptech.estore.services;

import vn.aptech.estore.entities.AttributeGroup;
import vn.aptech.estore.entities.Brand;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/26/2021
 * Time: 7:26 AM
 */
public interface AttributeGroupService {
    Iterable<AttributeGroup> findAll();

    AttributeGroup save(AttributeGroup attributeGroup);

    void deleteById(Long id);

    Optional<AttributeGroup> findById(Long id);
}
