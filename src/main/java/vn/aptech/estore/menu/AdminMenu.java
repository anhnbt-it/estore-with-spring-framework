package vn.aptech.estore.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/14/2021
 * Time: 6:47 PM
 */
@Component
public class AdminMenu extends BaseMenu {
    private static final int OPTION_CATEGORY = 1;
    private static final int OPTION_PRODUCT = 2;

    @Autowired
    private CategoryMenu categoryMenu;

    @Autowired
    private ProductMenu productMenu;

    @Autowired
    private MessageSource messageSource;

    public AdminMenu() {
        title = messageSource.getMessage("title.administrator", null, Locale.getDefault());
        menuItems.put(OPTION_CATEGORY, "Quản lý Danh mục");
        menuItems.put(OPTION_PRODUCT, "Quản lý Sản phẩm");
    }

    @Override
    public void start() {
        printMenuHeader();
        int choice = enterChoice();
        switch (choice) {
            case OPTION_CATEGORY:
                categoryMenu.start();
                break;
            case OPTION_PRODUCT:
                productMenu.start();
                break;
            default:
                showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.INVALID_OPTION);

        }
    }
}
