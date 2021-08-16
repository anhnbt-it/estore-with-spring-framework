package vn.aptech.estore.menu;

import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyá»…n BÃ¡ Tuáº¥n Anh <anhnbt.it@gmail.com>
 * Date: 8/16/2021
 * Time: 6:36 AM
 */
@Component
public class StatisticsMenu extends BaseMenu {


    public StatisticsMenu() {
        super("Báo cáo/Thống kê");
        menuItems.put(1, "Doanh thu sản phẩm theo ngày");
        menuItems.put(2, "Xem thống kê Đơn hàng theo ngày");
        menuItems.put(3, "Báo cáo doanh số");
        menuItems.put(4, "Báo cáo lợi nhuận");
    }

    @Override
    public void start() {
        printMenuHeader();
    }
}
