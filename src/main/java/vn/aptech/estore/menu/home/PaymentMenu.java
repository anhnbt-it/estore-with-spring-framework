package vn.aptech.estore.menu.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.Customer;
import vn.aptech.estore.entities.Order;
import vn.aptech.estore.menu.AuthMenu;
import vn.aptech.estore.menu.BaseMenu;
import vn.aptech.estore.services.OrderService;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/22/2021
 * Time: 11:27 AM
 */
@Component
public class PaymentMenu extends BaseMenu {

    @Autowired
    private AuthMenu authMenu;

    @Autowired
    private OrderService orderService;

    public PaymentMenu() {
        super("Thông tin thanh toán");
    }

    @Override
    public void start() {
        String choice = enterString("Bạn đã là thành viên? [y/N]: ");
        if (choice.equalsIgnoreCase("y")) {
            authMenu.start();
        } else {
            Order order = new Order();
            printMenuHeader("Địa chỉ giao hàng");
            String firstName = enterString("Họ tên: ", true);
            String fullName = enterString("Họ tên: ", true);
            String email = enterString("Email: ", true);
            String phone = enterString("Số điện thoại: ", true);
            String provinces = enterString("Tỉnh/Thành phố: ", true);
            String districts = enterString("Quận/Huyện: ", true);
            String wards = enterString("Phường/Xã: ", true);
            String address = enterString("Địa chỉ nhận hàng: ", true);
            Customer customer = new Customer();
            customer.setFirstName();
            printTitle("Chọn hình thức giao hàng");
            int shippingMethod = 1;
            System.out.println("1) [x] Giao hàng tận nơi");
            System.out.println("(Chỉ hỗ trợ Giao hàng tận nơi)");
            shippingMethod = enterInteger("Chọn hình thức giao hàng: ");
            if (shippingMethod != 1) {
                System.out.println("Hình thức giao hàng không hợp lệ");
            }
            System.out.println("1) [x] Thanh toán tiền mặt khi nhận hàng (Mặc định)");
            System.out.println("2) Chuyển khoản ngân hàng");
            int paymentMethod = 1;
            paymentMethod = enterInteger("Chọn hình thức thanh toán: ");
            if (paymentMethod < 1 || paymentMethod > 2) {
                System.out.println("Hình thức thanh toán không hợp lệ");
            }
            choice = enterString("Xác nhận thanh toán? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                Order newOrder = orderService.save(order);
                if (newOrder != null) {
                    System.out.println("Cảm ơn bạn đã đặt hàng");
                    System.out.println("Một email xác nhận đã được gửi tới " + email);
                    System.out.println("Xin vui lòng kiểm tra email của bạn");
                } else {
                    System.out.println("Đã xảy ra lỗi khi đặt hàng. Vui lòng thử lại");
                }
            }
        }
    }
}
