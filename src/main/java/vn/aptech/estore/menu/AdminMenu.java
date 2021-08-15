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
    private static final int OPTION_EMPLOYEE = 3;
    private static final int OPTION_CUSTOMER = 4;
    private static final int OPTION_ORDER = 5;
    private static final int OPTION_BRAND = 6;
    private static final int OPTION_SUPPLIER = 7;
    private static final int OPTION_STATISTICS = 8;

    @Autowired
    private CategoryMenu categoryMenu;

    @Autowired
    private ProductMenu productMenu;

    @Autowired
    private EmployeeMenu employeeMenu;

    @Autowired
    private CustomerMenu customerMenu;

    @Autowired
    private BrandMenu brandMenu;

    @Autowired
    private SupplierMenu supplierMenu;

    @Autowired
    private OrderMenu orderMenu;

    @Autowired
    private MessageSource messageSource;

    public AdminMenu() {
        super("Administrator");
        menuItems.put(OPTION_CATEGORY, "Quản lý Danh mục");
        menuItems.put(OPTION_PRODUCT, "Quản lý Sản phẩm");
        menuItems.put(OPTION_EMPLOYEE, "Quản lý Nhân viên");
        menuItems.put(OPTION_CUSTOMER, "Quản lý Khách hàng");
        menuItems.put(OPTION_ORDER, "Quản lý Hóa đơn");
        menuItems.put(OPTION_BRAND, "Quản lý Thương hiệu");
        menuItems.put(OPTION_SUPPLIER, "Quản lý Nhà cung cấp");
        menuItems.put(OPTION_STATISTICS, "Thống kê");
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
            case OPTION_EMPLOYEE:
                employeeMenu.start();
                break;
            case OPTION_CUSTOMER:
                customerMenu.start();
                break;
            case OPTION_ORDER:
                orderMenu.start();
            case OPTION_BRAND:
                brandMenu.start();
            case OPTION_SUPPLIER:
                supplierMenu.start();
                break;
            default:
                showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.INVALID_OPTION);

        }
    }
}
