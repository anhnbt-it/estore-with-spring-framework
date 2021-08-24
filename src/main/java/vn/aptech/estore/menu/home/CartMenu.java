package vn.aptech.estore.menu.home;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.menu.BaseMenu;
import vn.aptech.estore.services.ShoppingCartService;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/23/2021
 * Time: 2:02 PM
 */
@Component
public class CartMenu extends BaseMenu {
    private static final Logger logger = LoggerFactory.getLogger(CartMenu.class);
    private final ShoppingCartService shoppingCartService;
    private final PaymentMenu paymentMenu;

    public CartMenu(ShoppingCartService shoppingCartService, PaymentMenu paymentMenu) {
        super("Giỏ hàng");
        this.shoppingCartService = shoppingCartService;
        this.paymentMenu = paymentMenu;
    }

    @Override
    public void start() {
        Map<Long, Product> items = shoppingCartService.getItems();
        if (items.isEmpty()) {
            logger.info("Không có sản phẩm nào trong giỏ hàng!");
        } else {
            printTitle("Tất cả (" + items.size() + ") sản phẩm");
            System.out.printf("| %-5s | %-20s | %-15s | %-5s | %-5s | %-15s |%n", "ID", "Tên", "Đơn giá", "% Giảm", "Số lượng", "Thành tiền");
            for (Map.Entry<Long, Product> entry : items.entrySet()) {
                Product product = entry.getValue();
                double total = product.getUnitPrice() * product.getUnitsInStock();
                System.out.printf("| %-5s | %-20s | %-15s | %-5s | %-5s | %-15s |%n", product.getId(), StringCommon.truncate(product.getName(), 20),
                        StringCommon.convertDoubleToVND(product.getUnitPrice()), product.getDiscountStr(), product.getUnitsInStock(), StringCommon.convertDoubleToVND(total));
            }
            shoppingCartService.getNumberOfItems();
            System.out.println("Tổng Số Tiền (gồm VAT): " + StringCommon.convertDoubleToVND(shoppingCartService.getTotal()));
            String payment = enterString("Ban co muon thanh toan khong? [y/N]");
            if (payment.equalsIgnoreCase("y")) {
                paymentMenu.start();
            }
        }
    }
}
