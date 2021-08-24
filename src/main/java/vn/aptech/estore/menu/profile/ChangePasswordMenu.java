package vn.aptech.estore.menu.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.menu.BaseMenu;
import vn.aptech.estore.services.AuthService;
import vn.aptech.estore.services.UserService;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/24/2021
 * Time: 7:06 AM
 */
@Component
public class ChangePasswordMenu extends BaseMenu {

    @Autowired
    private UserService userService;

    public ChangePasswordMenu() {
        super("Thay đổi mật khẩu");
    }

    @Override
    public void start() {
        printMenuHeader();
        String oldPassword = enterString("Nhập mật khẩu cũ: ", true);
        String password = enterString("Nhập mật khẩu: ", true);
        String confirmPassword = enterString("Nhập lại mật khẩu: ", true);
        if (AuthService.user.getPassword().equals(oldPassword)) {
            if (password.equals(confirmPassword)) {
                AuthService.user.setPassword(password);
                if (userService.save(AuthService.user) != null) {
                    showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_UPDATED);
                } else {
                    showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
                }
            } else {
                System.out.println("Nhập lại mật khẩu không khớp");
            }
        } else {
            System.out.println("Mật khẩu cũ không đúng");
        }
    }
}
