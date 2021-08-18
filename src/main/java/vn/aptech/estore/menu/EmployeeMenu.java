package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Role;
import vn.aptech.estore.entities.User;
import vn.aptech.estore.services.UserService;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/15/2021
 * Time: 8:29 PM
 */
@Component
public class EmployeeMenu extends CRUDMenu {

    @Autowired
    private UserService userService;

    public EmployeeMenu() {
        super("Nhân viên");
    }

    @Override
    public void create() {
        printTitle("Thêm nhân viên");
        User user = new User();
        user.setUsername(enterString("Nhập tên đăng nhập: ", true));
        user.setPassword(enterString("Nhập mật khẩu: ", true));
        user.setEmail(enterString("Nhập email: ", true));
        user.setRole(Role.ROLE_STAFF);
        if (userService.save(user) != null) {
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
