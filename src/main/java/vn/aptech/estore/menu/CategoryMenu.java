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

import java.util.List;
import java.util.Locale;

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
        title = messageSource.getMessage("title.categories", null, Locale.getDefault());
    }

    @Override
    public void create() {
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

    }

    @Override
    public void delete() {

    }

    @Override
    public void showAll() {
        List<Category> categories = IterableUtils.toList(categoryService.findAll());
        if (categories.isEmpty()) {
            showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
        } else {
            printTitle("Danh sách Danh mục");
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Tên", "Ngày tạo");
            for (Category category : categories) {
                System.out.printf("| %-5s | %-20s | %-10s |%n", category.getId(), StringCommon.truncate(category.getName(), 20), StringCommon.dateFormat(category.getCreatedDate()));
            }
        }
    }

    @Override
    public void showOne() {

    }
}
