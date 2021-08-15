package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.Role;
import vn.aptech.estore.entities.User;
import vn.aptech.estore.services.AuthService;
import vn.aptech.estore.services.UserService;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/10/2021
 * Time: 8:38 PM
 */
@Component
public class AuthMenu extends BaseMenu {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminMenu adminMenu;

    public AuthMenu() {
        super("Đăng nhập");
    }

    @Override
    public void start() {
        printMenuHeader();
        System.out.println("Nhập tên tài khoản: ");
        String username = scanner.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = scanner.nextLine();
        Optional<User> user = userService.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            AuthService.user = user.get();
            System.out.println("Đăng nhập thành công");
            if (AuthService.user.getRole() == Role.ROLE_ADMIN) {
                adminMenu.start();
            }
        } else {
            System.out.println("Sai tài khoản hoặc mật khẩu");
        }
    }

    public void register() {
        this.printMenuHeader();
        System.out.println("Nhập tên tài khoản: ");
        String username = scanner.nextLine();
        System.out.println("Nhập mật khẩu: ");
        String password = scanner.nextLine();

        User user = userService.save(new User(username, password, Role.ROLE_USER));
        if (user == null) {
            System.out.println("Đã xảy ra lỗi. Vui lòng thử lại!");
        } else {
            System.out.println("Đăng ký thành công!");
        }
    }
}
