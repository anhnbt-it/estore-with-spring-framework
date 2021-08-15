package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.Brand;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.entities.Supplier;
import vn.aptech.estore.services.BrandService;
import vn.aptech.estore.services.CategoryService;
import vn.aptech.estore.services.ProductService;
import vn.aptech.estore.services.SupplierService;

import java.util.*;

@Component
public class ProductMenu extends CRUDMenu {
    private static final Logger log = LoggerFactory.getLogger(ProductMenu.class);

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MessageSource messageSource;

    public ProductMenu() {
        super("Sản phẩm");
    }

    @Override
    public void create() {

    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void showAll() {

    }

    @Override
    public void showOne() {

    }

    private void deleteProduct() {
        System.out.print("Bạn muốn tìm kiếm sản phẩm? [y/N]: ");
        System.out.println("Tìm thấy: '");
        System.out.println("Bạn chắc chắn muốn xóa sản phẩm ''? [y/N]: ");
        System.out.println("Vui lòng chờ...");
        System.out.println("Bạn không xóa sản phẩm ''");
        System.out.println("Xóa sản phẩm '' thành công!");
    }

    private void editProduct() {
    }

    private void addNewProduct(Scanner scanner) {
        try {
            Product product = new Product();
            System.out.println("Bạn muốn xem danh sách danh mục? [y/N]: ");
            String choice = scanner.nextLine();
            if ("y".equalsIgnoreCase(choice)) {
                List<Category> categories = IterableUtils.toList(categoryService.findAll());
                if (!categories.isEmpty()) {
                    for (Category category : categories) {
                        System.out.println(category.toString());
                    }
                } else {
                    System.out.println("Chưa có danh mục nào");
                    return;
                }
            }
            System.out.println("Các trường đánh dấu * là bắt buộc phải nhập");
            System.out.println("Nhập ID danh mục: ");
            long categoryId = scanner.nextLong();
            Optional<Category> category = categoryService.findById(categoryId);
            if (!category.isPresent()) {
                System.out.println("Không tìm thấy danh mục nào có ID là '" + categoryId + "'");
            } else {
                product.setCategory(category.get());
            }

            System.out.println("Bạn muốn xem danh sách nhà cung cấp? [y/N]: ");
            choice = scanner.nextLine();
            if ("y".equalsIgnoreCase(choice)) {
                List<Supplier> suppliers = IterableUtils.toList(supplierService.findAll());
                if (!suppliers.isEmpty()) {
                    for (Supplier supplier : suppliers) {
                        System.out.println(supplier.toString());
                    }
                } else {
                    System.out.println("Chưa có nhà cung cấp nào");
                    return;
                }
            }
            System.out.println("Nhập ID nhà cung cấp: ");
            long supplierId = scanner.nextLong();
            Optional<Supplier> supplier = supplierService.findById(supplierId);
            if (!supplier.isPresent()) {
                System.out.println("Không tìm thấy nhà cung cấp nào có ID là '" + supplierId + "'");
            } else {
                product.setSupplier(supplier.get());
            }
            System.out.println("Bạn muốn xem danh sách thương hiệu? [y/N]: ");
            choice = scanner.nextLine();
            if ("y".equalsIgnoreCase(choice)) {
                List<Brand> brands = IterableUtils.toList(brandService.findAll());
                if (!brands.isEmpty()) {
                    for (Brand brand : brands) {
                        System.out.println(brand.toString());
                    }
                } else {
                    System.out.println("Chưa có thương hiệu nào");
                    return;
                }
            }
            System.out.println("Nhập ID thương hiệu: ");
            long brandId = scanner.nextLong();
            Optional<Brand> brand = brandService.findById(brandId);
            if (!brand.isPresent()) {
                System.out.println("Không tìm thấy thương hiệu nào có ID là '" + brandId + "'");
            } else {
                product.setBrand(brand.get());
            }
            scanner.nextLine();
            System.out.println("Nhập tên: ");
            product.setName(scanner.nextLine());

            System.out.println("Nhập mô tả: ");
            product.setDescription(scanner.nextLine());

            System.out.println("Nhập ảnh: ");
            product.setThumbnailUrl(scanner.nextLine());

            System.out.println("Nhập giá: ");
            product.setUnitPrice(scanner.nextBigDecimal());

            System.out.println("Nhập giá so sánh: ");
            product.setCompareAtPrice(scanner.nextBigDecimal());

            System.out.println("Nhập % giảm giá: ");
            product.setDiscount(scanner.nextFloat());

            System.out.println("Nhập số lượng: ");
            product.setQuantity(scanner.nextInt());

            System.out.println("Nhập trạng thái [true - Hiển thị; false - Ẩn]: ");
            product.setStatus(scanner.nextBoolean());
            Product newProduct = productService.save(product);
            if (newProduct.getId() != null) {
                System.out.println("Thêm sản phẩm mới thành công!");
            } else {
                System.out.println("Thêm sản phẩm bị lỗi!");
            }
        } catch (InputMismatchException e) {
            log.error(e.getMessage());
        }
    }
}
