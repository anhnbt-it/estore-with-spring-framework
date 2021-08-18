package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Brand;
import vn.aptech.estore.services.BrandService;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/15/2021
 * Time: 8:28 PM
 */
@Component
public class BrandMenu extends CRUDMenu {

    @Autowired
    private BrandService brandService;

    public BrandMenu() {
        super("Thương hiệu");
    }

    @Override
    public void create() {
        printTitle("Thêm thương hiệu");
        Brand brand = new Brand();
        brand.setName(enterString("Nhập tên: ", true));
        if (brandService.save(brand) != null) {
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
