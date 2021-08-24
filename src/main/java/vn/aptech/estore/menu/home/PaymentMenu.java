package vn.aptech.estore.menu.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.*;
import vn.aptech.estore.menu.AuthMenu;
import vn.aptech.estore.menu.BaseMenu;
import vn.aptech.estore.services.*;

import java.util.Optional;

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

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    public PaymentMenu() {
        super("Thông tin thanh toán");
    }

    @Override
    public void start() {
        printTitle("Chọn hình thức giao hàng");
        String choice;
        Order order = new Order();
        Customer customer = new Customer();
        if (AuthService.user == null) {
            choice = enterString("Bạn đã là thành viên? [y/N]: ");
            if (choice.equalsIgnoreCase("y")) {
                authMenu.start();
            }
            String firstName = enterString("Họ: ", true);
            String lastName = enterString("Tên: ", true);
            String email = enterString("Email: ", true);
            String phone = enterString("Số điện thoại: ", true);
            // Ẩn tạm vì chưa hoàn thiện entity
//            String provinces = enterString("Tỉnh/Thành phố: ", true);
//            String districts = enterString("Quận/Huyện: ", true);
//            String wards = enterString("Phường/Xã: ", true);
//            String address = enterString("Địa chỉ nhận hàng: ", true);
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPhone(phone);
        } else {
            customer.setFirstName(AuthService.user.getFirstName());
            customer.setLastName(AuthService.user.getLastName());
            customer.setEmail(AuthService.user.getEmail());
            customer.setPhone(AuthService.user.getPhone());
        }
        int shippingMethod = 1;
        System.out.println("1) Giao hàng tận nơi");
        shippingMethod = enterInteger("Chọn hình thức giao hàng: ");
        if (shippingMethod != 1) {
            System.out.println("Hình thức giao hàng không hợp lệ");
        }
        System.out.println("1) Thanh toán tiền mặt khi nhận hàng");
        System.out.println("2) Chuyển khoản ngân hàng");
        int paymentMethod = 1;
        paymentMethod = enterInteger("Chọn hình thức thanh toán: ");
        if (paymentMethod < 1 || paymentMethod > 2) {
            System.out.println("Hình thức thanh toán không hợp lệ");
        }
        choice = enterString("Xác nhận thanh toán? [y/N]: ", true);
        if ("y".equalsIgnoreCase(choice)) {
            order.setCustomer(customer);
            order.setPayment(String.valueOf(paymentMethod));
            order.setShipping(String.valueOf(shippingMethod));
            Order newOrder = orderService.save(order);
            if (newOrder != null) {
                for (Product product : shoppingCartService.getProducts()) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderDetailId(new OrderDetailId(newOrder.getId(), product.getId()));
                    orderDetail.setProduct(product);
                    orderDetail.setQuantity(product.getUnitsInStock());
                    orderDetail.setPrice(product.getUnitPrice());
                    orderDetailService.save(orderDetail);
                    Optional<Product> prod = productService.findById(orderDetail.getProduct().getId());
                    if (prod.isPresent()) {
                        prod.get().setUnitsInStock(prod.get().getUnitsInStock() - orderDetail.getQuantity());
                        prod.get().setUnitsOnOrder(prod.get().getUnitsOnOrder() + orderDetail.getQuantity());
                        productService.save(prod.get());
                    }
                }
                // Tạo đơn hàng
                // Trừ số lượng sản phẩm trong kho
                System.out.println("Cảm ơn bạn đã đặt hàng");
                System.out.println("Một email xác nhận đã được gửi tới " + customer.getEmail());
                System.out.println("Xin vui lòng kiểm tra email của bạn");
            }
        }
    }
}
