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
 * Date: 8/24/2021
 * Time: 8:10 PM
 */
@Component
public class PopularProductMenu extends BaseMenu {

    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    public PopularProductMenu() {
        super("Sản phẩm bán chạy");
    }

    @Override
    public void start() {
        printMenuHeader();
        List<Product> products = IterableUtils.toList(productService.findAllByOrderByUnitsOnOrderDesc());
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
                System.out.println("Không có sản phẩm nào");
            } else {
                int qty = enterInteger("Nhap so luong: ", true);
                if (qty < 1) {
                    System.out.println("So luong toi thieu phai la 1");
                } else if (qty > product.get().getQuantity()) {
                    System.out.println("* Số lượng tối đa được phép mua: " + product.get().getQuantity());
                    qty = product.get().getQuantity();
                }
                product.get().setQuantity(qty);
                shoppingCartService.addToCart(product.get());
            }
        }
    }
}
