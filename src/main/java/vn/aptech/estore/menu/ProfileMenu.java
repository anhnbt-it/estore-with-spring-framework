package vn.aptech.estore.menu;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/15/2021
 * Time: 8:29 PM
 */
@Component
public class ProfileMenu extends BaseMenu {
    public ProfileMenu() {
        super("Thông tin cá nhân");
        menuItems.put(1, "Thay đổi mật khẩu");
        menuItems.put(2, "Thay đổi thông tin cá nhân");
    }

    @Override
    public void start() {
        printMenuHeader();
    }
}
