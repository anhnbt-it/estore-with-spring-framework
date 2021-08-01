package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.Brand;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.entities.Product;
import vn.aptech.estore.entities.Supplier;
import vn.aptech.estore.services.BrandService;
import vn.aptech.estore.services.CategoryService;
import vn.aptech.estore.services.ProductService;
import vn.aptech.estore.services.SupplierService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ProductMenu implements BaseMenu, CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ProductMenu.class);

    private static final int USER_OPTION_FIND_ALL = 1;
    private static final int USER_OPTION_INSERT = 2;
    private static final int USER_OPTION_UPDATE = 3;
    private static final int USER_OPTION_DELETE = 4;
    private static final int USER_OPTION_FIND_ONE = 5;
    private static final int USER_OPTION_EXIT = 0;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private ProductService productService;

    @Override
    public void printMenuHeader() {
        System.out.println("Sản phẩm");
        System.out.println("1. Tất cả sản phẩm");
        System.out.println("2. Thêm mới sản phẩm");
        System.out.println("3. Sửa sản phẩm");
        System.out.println("4. Xóa sản phẩm");
        System.out.println("5. Xem sản phẩm");
        System.out.println("0. Quay lại");
    }

    @Override
    public void start(Scanner scanner) {
        int choice;
        try {
            do {
                this.printMenuHeader();
                System.out.print("Nhập lựa chọn [0-5]: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case USER_OPTION_FIND_ALL:
                        this.showAll();
                        break;
                    case USER_OPTION_FIND_ONE:
                        this.showOne();
                        break;
                    case USER_OPTION_INSERT:
                        this.addNewProduct(scanner);
                        break;
                    case USER_OPTION_UPDATE:
                        this.editProduct();
                        break;
                    case USER_OPTION_DELETE:
                        this.deleteProduct();
                        break;
                    case USER_OPTION_EXIT:
                        return;
                    default:
                        System.out.println("Lua chon khong dung!");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteProduct() {
    }

    private void editProduct() {
    }

    private void addNewProduct(Scanner scanner) {
        try {

            Product product = new Product();
//        List<Category> categories = IterableUtils.toList(categoryService.findAll());
//        if (!categories.isEmpty()) {
//            for (Category category : categories) {
//                System.out.println(category.toString());
//            }
//        }
            System.out.println("Chọn danh mục: ");
            long categoryId = scanner.nextLong();
            Optional<Category> category = categoryService.findById(categoryId);
            category.ifPresent(product::setCategory);

            System.out.println("Chọn nhà cung cấp: ");
            long supplierId = scanner.nextLong();
            Optional<Supplier> supplier = supplierService.findById(supplierId);
            supplier.ifPresent(product::setSupplier);

            System.out.println("Chọn thương hiệu: ");
            long brandId = scanner.nextLong();
            Optional<Brand> brand = brandService.findById(brandId);
            brand.ifPresent(product::setBrand);

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
                System.out.println("Thêm sản phẩm mới lỗi!");
            }
        } catch (InputMismatchException e) {
            log.error(e.getMessage());
        }
    }

    private void showOne() {
    }

    private void showAll() {
    }

    @Override
    public void run(String... args) throws Exception {
        this.start(new Scanner(System.in));
    }
}
