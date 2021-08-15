package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.constant.Response;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.services.*;

import java.util.ArrayList;
import java.util.List;

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

    public CategoryMenu() {
        super("Danh mục");
    }

    @Override
    public void create() {
        Category category = new Category();
        category.setName(enterString("Nhập tên: ", true));
        if (categoryService.save(category) != null) {
            showMsg(Constant.MESSAGE_TYPE.SUCCESS, String.format(Constant.Response.OBJECT_CREATED, category.getName()));
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
            List<String> columns = new ArrayList<>();
            columns.add("ID");
            columns.add("Tên");
            columns.add("Ngày tạo");
            showTableHeader(columns);
            for (Category category : categories) {
                List<Object> rows = new ArrayList<>();
                rows.add(category.getId());
                rows.add(category.getName());
                rows.add(StringCommon.dateFormat(category.getCreatedDate()));
                showTableRow(rows);
            }
        }
    }

    @Override
    public void showOne() {

    }
}
