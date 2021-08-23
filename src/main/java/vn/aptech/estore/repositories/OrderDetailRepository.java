package vn.aptech.estore.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.aptech.estore.entities.OrderDetail;
import vn.aptech.estore.entities.OrderDetailId;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/23/2021
 * Time: 8:39 PM
 */
@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, OrderDetailId> {
}
