package vn.aptech.estore.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.Role;
import vn.aptech.estore.entities.User;
import vn.aptech.estore.menu.home.HomeMenu;
import vn.aptech.estore.services.UserService;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/10/2021
 * Time: 8:57 PM
 */
@Component
public class MainMenu extends BaseMenu implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MainMenu.class);

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;

    @Autowired
    private AuthMenu authMenu;

    @Autowired
    private HomeMenu homeMenu;

    @Autowired
    private MessageSource messageSource;

    private static final int USER_OPTION_LOGIN = 1;
    private static final int USER_OPTION_REGISTER = 2;
    private static final int USER_OPTION_SHOPPING = 3;
    private static final int USER_OPTION_EXIT = 0;

    public MainMenu() {
        super("He thong quan ly ban hang");
        menuItems.put(1, "Đăng nhập");
        menuItems.put(2, "Đăng ký");
        menuItems.put(3, "Xem cửa hàng");
        menuItems.put(0, "Thoát");
    }

    @Override
    public void start() {
        int choice;
        try {
            do {
                printMenuHeader();
                System.out.println(messageSource.getMessage("message.choice.enter", null, new Locale("en", "US")));
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case USER_OPTION_LOGIN:
                        authMenu.start();
                        break;
                    case USER_OPTION_REGISTER:
                        authMenu.register();
                        break;
                    case USER_OPTION_SHOPPING:
                        homeMenu.start();
                        break;
                    case USER_OPTION_EXIT:
                        System.exit(0);
                    default:
                        System.out.println("Bạn nhập sai, vui lòng nhập theo số thứ tự trên menu!");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void run(String... args) {
        if (!userService.findByUsername("admin").isPresent()) {
            User admin = new User();
            admin.setFirstName("Nguyen Ba Tuan");
            admin.setLastName("Anh");
            admin.setEmail("admin@gmail.com");
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(Role.ROLE_ADMIN);
            userService.save(admin);
            log.info("Create admin account 'admin' password: 'admin'");
        }
        log.info("-------------------------------");
        if (!userService.findByUsername("user").isPresent()) {
            User user = new User();
            user.setFirstName("Khoai");
            user.setLastName("Tay");
            user.setEmail("user@gmail.com");
            user.setUsername("user");
            user.setPassword("user");
            user.setRole(Role.ROLE_USER);
            userService.save(user);
            log.info("Create user account 'user' password: 'user'");
        }
        log.info("-------------------------------");

        this.start();
    }
}
