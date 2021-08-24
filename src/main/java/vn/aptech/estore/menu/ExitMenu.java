package vn.aptech.estore.menu;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyá»…n BÃ¡ Tuáº¥n Anh <anhnbt.it@gmail.com>
 * Date: 8/16/2021
 * Time: 6:52 AM
 */
@Component
public class ExitMenu extends BaseMenu {
    public ExitMenu() {
        super("Exit");
    }

    @Override
    public void start() {
        String choice = enterString("Bạn có chắc chắn muốn đóng ứng dụng? [y/N]: ");
        if ("y".equalsIgnoreCase(choice)) {
            System.out.println("Bye bye...");
            System.exit(0);
        }
    }
}
