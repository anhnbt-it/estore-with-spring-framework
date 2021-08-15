package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.Role;
import vn.aptech.estore.entities.User;
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

    public AuthMenu() {
        title = messageSource.getMessage("title.auth.signIn", null, Locale.getDefault());
    }

    @Override
    public void start() {
        printMenuHeader();
        System.out.println(messageSource.getMessage("enter.field.name", new Object[]{}, Locale.getDefault()));
        String username = scanner.nextLine();
        System.out.println(messageSource.getMessage("enter.field.password", new Object[]{}, Locale.getDefault()));
        String password = scanner.nextLine();
        Optional<User> user = userService.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            AuthService.user = user.get();
            System.out.println(messageSource.getMessage("message.loggedInUser", new Object[]{AuthService.user.getUsername()}, Locale.getDefault()));
            if (AuthService.user.getRole() == Role.ROLE_ADMIN) {
                adminMenu.start();
            }
        } else {
            System.out.println(messageSource.getMessage("message.login.failed", new Object[]{}, Locale.getDefault()));
        }
    }

    public void register() {
        this.printMenuHeader();
        System.out.println(messageSource.getMessage("enter.field.name", new Object[]{}, Locale.getDefault()));
        String username = scanner.nextLine();
        System.out.println(messageSource.getMessage("enter.field.password", new Object[]{}, Locale.getDefault()));
        String password = scanner.nextLine();

        User user = userService.save(new User(username, password, Role.ROLE_USER));
        if (user == null) {
            System.out.println(messageSource.getMessage("message.error.system", new Object[]{}, Locale.getDefault()));
        } else {
            System.out.println(messageSource.getMessage("message.register.success", new Object[]{}, Locale.getDefault()));
        }
    }
}
