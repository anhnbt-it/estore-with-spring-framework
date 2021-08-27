package vn.aptech.estore.menu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.Role;
import vn.aptech.estore.entities.User;
import vn.aptech.estore.exception.CommonException;
import vn.aptech.estore.services.AuthService;
import vn.aptech.estore.services.UserService;

import java.util.Locale;
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

    @Autowired
    private MessageSource messageSource;
    private static final Logger LOGGER = LogManager.getLogger();

    public AuthMenu() {
        super("Sign in");
    }

    @Override
    public void start() {
        try {
            printMenuHeader();
            String username = enterString(messageSource.getMessage("enter.field.name", new Object[]{}, Locale.getDefault()), true);
            String password = enterString(messageSource.getMessage("enter.field.password", new Object[]{}, Locale.getDefault()), true);
            Optional<User> user = userService.findByUsernameAndPassword(username, password);
            if (user.isPresent()) {
                AuthService.user = user.get();
                System.out.println(messageSource.getMessage("message.loggedInUser", new Object[]{AuthService.user.getUsername()}, Locale.getDefault()));
                if (AuthService.user.getRole() == Role.ROLE_ADMIN) {
                    adminMenu.start();
                }
            } else {
                System.err.println(messageSource.getMessage("message.login.failed", null, Locale.getDefault()));
            }
        } catch (Exception e) {
            LOGGER.error(Level.ERROR, e);
        }
    }

    public void register() {
        try {
            printTitle("Đăng ký");
            String username = enterString(messageSource.getMessage("enter.field.name", null, Locale.getDefault()), true);
            String password = enterString(messageSource.getMessage("enter.field.password", null, Locale.getDefault()), true);
            String confirmPassword = enterString(messageSource.getMessage("field.confirmPassword", null, Locale.getDefault()), true);
            if (!password.equalsIgnoreCase(confirmPassword)) {
                System.err.println(messageSource.getMessage("validators.password.confirm", null, Locale.getDefault()));
            }
            User user = userService.save(new User(username, password, Role.ROLE_USER));
            if (user == null) {
                System.err.println(messageSource.getMessage("message.error.system", null, Locale.getDefault()));
            } else {
                System.err.println(messageSource.getMessage("message.register.success", null, Locale.getDefault()));
            }
        } catch (CommonException e) {
            System.err.println(e.getMessage());
        }
    }
}
