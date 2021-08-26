package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.estore.entities.AttributeGroup;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/26/2021
 * Time: 7:23 AM
 */
@Repository
public interface AttributeGroupRepository extends CrudRepository<AttributeGroup, Long> {
}
