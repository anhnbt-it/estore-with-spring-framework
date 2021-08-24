package vn.aptech.estore.menu.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.menu.BaseMenu;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/15/2021
 * Time: 8:29 PM
 */
@Component
public class ProfileMenu extends BaseMenu {

    public static final int OPTION_CHANGE_PASSWORD = 1;
    public static final int OPTION_UPDATE_PROFILE = 2;

    @Autowired
    private ChangePasswordMenu changePasswordMenu;
    @Autowired
    private EditProfile editProfile;

    public ProfileMenu() {
        super("Thông tin cá nhân");
        menuItems.put(1, "Thay đổi mật khẩu");
        menuItems.put(2, "Thay đổi thông tin cá nhân");
    }

    @Override
    public void start() {
        int choice = enterChoice();
        do {
            printMenuHeader();
            switch (choice) {
                case OPTION_CHANGE_PASSWORD:
                    changePasswordMenu.start();
                    break;
                case OPTION_UPDATE_PROFILE:
                    editProfile.start();
                    break;
                default:
                    System.out.println("Bạn nhập sai, vui lòng nhập theo số thứ tự trên menu!");
            }
        } while (true);
    }
}
