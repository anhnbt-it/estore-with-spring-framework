package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Supplier;
import vn.aptech.estore.services.SupplierService;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/15/2021
 * Time: 8:28 PM
 */
@Component
public class SupplierMenu extends CRUDMenu {

    @Autowired
    private SupplierService supplierService;

    public SupplierMenu() {
        super("Nhà cung cấp");
    }

    @Override
    public void create() {
        printTitle("Thêm nhà cung cấp");
        Supplier supplier = new Supplier();
        supplier.setName(enterString("Nhập tên: ", true));
        if (supplierService.save(supplier) != null) {
            showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_CREATED);
        } else {
            showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void showAll() {

    }

    @Override
    public void showOne() {

    }
}
