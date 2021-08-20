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

import java.util.Enumeration;
import java.util.Hashtable;
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
        printMenuHeader();
        int choice = enterChoice();
        switch (choice) {
            case OPTION_RECENT_PRODUCT:
                List<Product> products = IterableUtils.toList(productService.findAllByOrderByCreatedDateDesc());
                if (products.isEmpty()) {
                    showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
                } else {
                    System.out.printf("| %-5s | %-20s | %-15s | %-5s | %-15s |%n", "ID", "Tên", "Giá", "% Giảm", "Giá giảm");
                    for (Product product : products) {
                        System.out.printf("| %-5s | %-20s | %-15s | %-5s | %-15s |%n", product.getId(), StringCommon.truncate(product.getName(), 20),
                                StringCommon.convertDoubleToVND(product.getUnitPrice()), product.getDiscountStr(), StringCommon.convertDoubleToVND(product.getCompareAtPrice()));
                    }
                    long productId = enterInteger("Nhập ID sản phẩm bạn muốn mua: ", true);
                    Optional<Product> product = productService.findById(productId);
                    if (!product.isPresent()) {
                        System.out.println("Khong tim thay san pham");
                    } else {
                        int qty = enterInteger("Nhap so luong: ", true);
                        if (qty < 1) {
                            System.out.println("So luong toi thieu phai la 1");
                        } else if (qty > product.get().getQuantity()) {
                            System.out.println("Xin loi, san pham khong du so luong. Ban co the mua toi da la " + product.get().getQuantity());
                            qty = product.get().getQuantity();
                        }
                        product.get().setQuantity(qty);
                        shoppingCartService.addToCart(product.get());
                    }
                }
                break;
            case OPTION_POPULAR_PRODUCT:
                break;
            case OPTION_ALL_CATEGORIES:
                break;
            case OPTION_SHOPPING_CART:
                Hashtable<Long, Product> items = shoppingCartService.getItems();
                if (items.isEmpty()) {
                    System.out.println("Gio hang cua ban trong!");
                } else {
                    printTitle("Gio hang co " + items.size() + " san pham");
                    System.out.printf("| %-5s | %-20s | %-15s | %-5s | %-5s | %-15s |%n", "ID", "Tên", "Đơn giá", "% Giảm", "Số lượng", "Thành tiền");
                    Enumeration<Long> enu = items.keys();
                    while (enu.hasMoreElements()) {
                        long key = enu.nextElement();
                        Product product = items.get(key);
                        double total = product.getUnitPrice() * product.getQuantity();
                        System.out.printf("| %-5s | %-20s | %-15s | %-5s | %-5s | %-15s |%n", product.getId(), StringCommon.truncate(product.getName(), 20),
                                StringCommon.convertDoubleToVND(product.getUnitPrice()), product.getDiscountStr(), product.getQuantity(), StringCommon.convertDoubleToVND(total));
                    }
                    shoppingCartService.getNumberOfItems();
                    System.out.println("Cart subtotal (" + items.size() + " items): " + StringCommon.convertDoubleToVND(shoppingCartService.getTotal()));
                }
                break;
            case OPTION_PROFILE:
                break;
            case OPTION_BACK:
                return;
            default:
                System.out.println("Bạn nhập sai, vui lòng nhập theo số thứ tự trên menu!");
        }
    }
}
