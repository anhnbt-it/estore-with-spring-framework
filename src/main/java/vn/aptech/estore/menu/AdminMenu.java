package vn.aptech.estore.menu;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.menu.home.HomeMenu;
import vn.aptech.estore.menu.profile.ProfileMenu;
import vn.aptech.estore.services.AuthService;

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
    private static final int OPTION_PROFILE = 9;
    private static final int OPTION_SIGNOUT = 10;
    private static final int OPTION_SHOPPING = 11;
    private static final int OPTION_ATTRIBUTE_GROUP = 12;
    private static final int OPTION_BACK = 0;

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
    private StatisticsMenu statisticsMenu;

    @Autowired
    private ProfileMenu profileMenu;

    @Autowired
    private SignOutMenu signOutMenu;

    @Autowired
    private HomeMenu homeMenu;

    @Autowired
    private AttributeGroupMenu attributeGroupMenu;

    @Autowired
    private ExitMenu exitMenu;

    @Autowired
    private MessageSource messageSource;
    private static final Logger LOGGER = LogManager.getLogger();

    public AdminMenu() {
        super("Administrator");
        menuItems.put(OPTION_CATEGORY, "Quản lý Danh mục");
        menuItems.put(OPTION_PRODUCT, "Quản lý Sản phẩm");
        menuItems.put(OPTION_EMPLOYEE, "Quản lý Nhân viên");
        menuItems.put(OPTION_CUSTOMER, "Quản lý Khách hàng");
        menuItems.put(OPTION_ORDER, "Quản lý Hóa đơn");
        menuItems.put(OPTION_BRAND, "Quản lý Thương hiệu");
        menuItems.put(OPTION_SUPPLIER, "Quản lý Nhà cung cấp");
        menuItems.put(OPTION_STATISTICS, "Báo cáo/Thống kê");
        menuItems.put(OPTION_PROFILE, "Tài khoản");
        menuItems.put(OPTION_SIGNOUT, "Đăng xuất");
        menuItems.put(OPTION_SHOPPING, "Cửa hàng");
        menuItems.put(OPTION_ATTRIBUTE_GROUP, "Quản lý Nhóm thuộc tính");
        menuItems.put(OPTION_BACK, "Quay lại");

    }

    @Override
    public void start() {
        try {
            int choice;
            do {
                printMenuHeader("Xin chào: " + AuthService.user.getUsername());
                choice = enterChoice();
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
                        break;
                    case OPTION_BRAND:
                        brandMenu.start();
                        break;
                    case OPTION_SUPPLIER:
                        supplierMenu.start();
                        break;
                    case OPTION_STATISTICS:
                        statisticsMenu.start();
                        break;
                    case OPTION_PROFILE:
                        profileMenu.start();
                        break;
                    case OPTION_SIGNOUT:
                        signOutMenu.start();
                        break;
                    case OPTION_SHOPPING:
                        homeMenu.start();
                        break;
                    case OPTION_ATTRIBUTE_GROUP:
                        attributeGroupMenu.start();
                        break;
                    case OPTION_BACK:
                        exitMenu.start();
                        break;
                    default:
                        showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.INVALID_OPTION);

                }
            } while (choice != 0);
        } catch (Exception e) {
            LOGGER.log(Level.ERROR, e);
        }
    }
}
