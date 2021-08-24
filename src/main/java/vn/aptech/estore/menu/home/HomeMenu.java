package vn.aptech.estore.menu.home;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.menu.BaseMenu;
import vn.aptech.estore.services.ProductService;
import vn.aptech.estore.services.ShoppingCartService;

import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/20/2021
 * Time: 7:11 AM
 */
@Component
public class HomeMenu extends BaseMenu {

    public static final int OPTION_RECENT_PRODUCT = 1;
    public static final int OPTION_POPULAR_PRODUCT = 2;
    public static final int OPTION_ALL_CATEGORIES = 3;
    public static final int OPTION_SHOPPING_CART = 4;
    public static final int OPTION_PROFILE = 5;
    public static final int OPTION_BACK = 0;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private CartMenu cartMenu;

    @Autowired
    private PopularProductMenu popularProductMenu;

    @Autowired
    private RecentProductMenu recentProductMenu;

    public HomeMenu() {
        super("Cửa hàng");
        this.menuItems.put(1, "Sản phẩm mới nhất");
        this.menuItems.put(2, "Sản phẩm bán chạy");
        this.menuItems.put(3, "Tất cả danh mục");
        this.menuItems.put(4, "Giỏ hàng");
        this.menuItems.put(5, "Tài khoản");
        this.menuItems.put(0, "Quay lại");
    }

    @Override
    public void start() {
        do {
            printMenuHeader();
            int choice = enterChoice();
            switch (choice) {
                case OPTION_RECENT_PRODUCT:
                    recentProductMenu.start();
                    break;
                case OPTION_POPULAR_PRODUCT:
                    popularProductMenu.start();
                    break;
                case OPTION_ALL_CATEGORIES:
                    break;
                case OPTION_SHOPPING_CART:
                    cartMenu.start();
                    break;
                case OPTION_PROFILE:
                    break;
                case OPTION_BACK:
                    return;
                default:
                    System.out.println("Bạn nhập sai, vui lòng nhập theo số thứ tự trên menu!");
            }
        } while (true);
    }
}
