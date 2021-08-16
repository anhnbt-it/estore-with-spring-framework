package vn.aptech.estore.menu;

import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.services.AuthService;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyá»…n BÃ¡ Tuáº¥n Anh <anhnbt.it@gmail.com>
 * Date: 8/16/2021
 * Time: 6:50 AM
 */
@Component
public class SignOutMenu extends BaseMenu {
    public SignOutMenu() {
        super("Sign out");
    }

    @Override
    public void start() {
        AuthService.user = null;
        showMsg(Constant.MESSAGE_TYPE.SUCCESS, "Đăng xuất thành công");
    }
}
