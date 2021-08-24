package vn.aptech.estore.menu.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.menu.BaseMenu;
import vn.aptech.estore.services.AuthService;
import vn.aptech.estore.services.UserService;

import java.sql.Timestamp;
import java.text.ParseException;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/24/2021
 * Time: 7:07 AM
 */
@Component
public class EditProfile extends BaseMenu {

    @Autowired
    private UserService userService;

    public EditProfile() {
        super("Thay đổi thông tin cá nhân");
    }

    @Override
    public void start() {
        try {
            printMenuHeader();
            AuthService.user.setFirstName(enterString("Nhập họ: ", true));
            AuthService.user.setLastName(enterString("Nhập tên: ", true));
            AuthService.user.setEmail(enterString("Nhập email: ", true));
            AuthService.user.setPhone(enterString("Nhập số điện thoại: ", true));
            AuthService.user.setGender(enterBoolean("Chọn giới tính [true=Nam;false=Nữ]: "));
            AuthService.user.setDateOfBirth(enterDate("Nhập năm/tháng/ngày sinh: "));
            AuthService.user.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            if (userService.save(AuthService.user) != null) {
                showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_UPDATED);
            } else {
                showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
