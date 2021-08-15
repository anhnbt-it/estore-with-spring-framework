package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.services.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class CategoryMenu extends CRUDMenu {
    private static final Logger log = LoggerFactory.getLogger(CategoryMenu.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    public CategoryMenu() {
        super("Danh mục");
    }

    @Override
    public void create() {
        printTitle("Thêm danh mục");
        Category category = new Category();
        category.setName(enterString("Nhập tên: ", true));
        if (categoryService.save(category) != null) {
            showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_CREATED);
        } else {
            showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
        }
    }

    @Override
    public void update() {
        printTitle("Chỉnh sửa danh mục");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách danh mục không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long categoryId = enterInteger("Nhập ID danh mục muốn chỉnh sửa: ");
            Optional<Category> category = categoryService.findById(categoryId);
            if (!category.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                System.out.println("Tìm thấy danh mục " + category.get().getName());
                category.get().setName(enterString("Nhập tên: ", true));
                category.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
                if (categoryService.save(category.get()) != null) {
                    showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_UPDATED);
                } else {
                    showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
                }
                choice = enterString("Bạn muốn chỉnh sửa danh mục khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }

    @Override
    public void delete() {
        printTitle("Xóa danh mục");
        String choice = enterString("Bạn có muốn xem Danh sách danh mục không? [y/N]: ", true);
        if ("y".equalsIgnoreCase(choice)) {
            showAll();
        }
        long categoryId = enterInteger("Nhập ID danh mục muốn xóa: ");
        Optional<Category> category = categoryService.findById(categoryId);
        if (!category.isPresent()) {
            System.out.println("Không tìm thấy");
        } else {
            System.out.println("Tìm thấy danh mục " + category.get().getName());
            choice = enterString("Bạn có chắc chắn muốn xóa danh mục này? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                categoryService.deleteById(categoryId);
                showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_DELETED);
            } else {
                System.out.println("Bạn chọn không xóa danh mục " + category.get().getName());
            }
        }
    }

    @Override
    public void showAll() {
        printTitle("Danh sách Danh mục");
        List<Category> categories = IterableUtils.toList(categoryService.findAll());
        if (categories.isEmpty()) {
            showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
        } else {
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Tên", "Ngày tạo");
            for (Category category : categories) {
                System.out.printf("| %-5s | %-20s | %-10s |%n", category.getId(), StringCommon.truncate(category.getName(), 20), StringCommon.dateFormat(category.getCreatedDate(), Constant.DATE_FORMAT));
            }
        }
    }

    @Override
    public void showOne() {
        printTitle("Xem chi tiết Danh mục");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách danh mục không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long categoryId = enterInteger("Nhập ID danh mục muốn xem: ");
            Optional<Category> category = categoryService.findById(categoryId);
            if (!category.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                printDivider();
                System.out.println("Tên: " + category.get().getName());
                System.out.println("Thời gian tạo: " + StringCommon.dateFormat(category.get().getCreatedDate(), Constant.DATE_TIME_FORMAT));
                System.out.println("Thời gian cập nhật: " + StringCommon.dateFormat(category.get().getModifiedDate(), Constant.DATE_TIME_FORMAT));
                printDivider();
                choice = enterString("Bạn muốn xem danh mục khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }
}
