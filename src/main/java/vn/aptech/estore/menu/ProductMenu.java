package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.*;
import vn.aptech.estore.services.*;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private AttributeGroupService attributeGroupService;

    @Autowired
    private MessageSource messageSource;

    @Value("${uploadPath}")
    private String uploadPath;

    public ProductMenu() {
        super("Sản phẩm");
    }

    @Override
    public void create() {
        try {
            printTitle("Thêm mới sản phẩm");
            printTitle("Bước 1: Nhập thông tin cơ bản");
            Product product = new Product();
            String choice = enterString("Bạn muốn xem danh sách danh mục? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                List<Category> categories = IterableUtils.toList(categoryService.findAll());
                if (!categories.isEmpty()) {
                    printTitle("Danh sách danh mục");
                    System.out.printf("| %-5s | %-20s |%n", "ID", "Tên");
                    for (Category category : categories) {
                        System.out.printf("| %-5s | %-20s |%n", category.getId(), StringCommon.truncate(category.getName(), 20));
                    }
                } else {
                    System.out.println("Chưa có danh mục nào");
                    return;
                }
            }
            System.out.println("Các trường đánh dấu * là bắt buộc phải nhập");
            long categoryId = enterInteger("Nhập ID danh mục: ", true);
            Optional<Category> category = categoryService.findById(categoryId);
            if (!category.isPresent()) {
                System.out.println("Không tìm thấy danh mục nào có ID là '" + categoryId + "'");
            } else {
                product.setCategory(category.get());
            }

            choice = enterString("Bạn muốn xem danh sách nhà cung cấp? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                List<Supplier> suppliers = IterableUtils.toList(supplierService.findAll());
                if (!suppliers.isEmpty()) {
                    printTitle("Danh sách nhà cung cấp");
                    System.out.printf("| %-5s | %-20s |%n", "ID", "Tên");
                    for (Supplier supplier : suppliers) {
                        System.out.printf("| %-5s | %-20s |%n", supplier.getId(), StringCommon.truncate(supplier.getName(), 20));
                    }
                } else {
                    System.out.println("Chưa có nhà cung cấp nào");
                    return;
                }
            }
            long supplierId = enterInteger("Nhập ID nhà cung cấp: ", true);
            Optional<Supplier> supplier = supplierService.findById(supplierId);
            if (!supplier.isPresent()) {
                System.out.println("Không tìm thấy nhà cung cấp nào có ID là '" + supplierId + "'");
            } else {
                product.setSupplier(supplier.get());
            }
            choice = enterString("Bạn muốn xem danh sách thương hiệu? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                List<Brand> brands = IterableUtils.toList(brandService.findAll());
                if (!brands.isEmpty()) {
                    printTitle("Danh sách thương hiệu");
                    System.out.printf("| %-5s | %-20s |%n", "ID", "Tên");
                    for (Brand brand : brands) {
                        System.out.printf("| %-5s | %-20s |%n", brand.getId(), StringCommon.truncate(brand.getName(), 20));
                    }
                } else {
                    System.out.println("Chưa có thương hiệu nào");
                    return;
                }
            }
            long brandId = enterInteger("Nhập ID thương hiệu: ", true);
            Optional<Brand> brand = brandService.findById(brandId);
            if (!brand.isPresent()) {
                System.out.println("Không tìm thấy thương hiệu nào có ID là '" + brandId + "'");
            } else {
                product.setBrand(brand.get());
            }
            product.setName(enterString("Nhập tên: ", true));
            product.setDescription(enterString("Nhập mô tả [Toi da 5000 ky tu]: "));
            product.setUnitPrice(enterDouble("Nhập giá: ", true));
            product.setDiscountPercent(enterFloat("Nhập % giảm giá (Ví dụ: 0.1 tương đương 10%): "));
            product.setCompareAtPrice(product.getUnitPrice() - product.getUnitPrice() * product.getDiscountPercent());
            product.setUnitsInStock(enterInteger("Nhập số lượng: ", true));
            product.setStatus(enterBoolean("Nhập trạng thái [true - Hiển thị; false - Ẩn]: "));
            printTitle("Bước 2: Nhập ảnh sản phẩm");
            List<Image> images = new ArrayList<>();
            do {
                Image image = new Image();
                String thumbUrl = enterString("Nhập đường đến tập tin ảnh trong máy: ", true);
                Path target = copyFile(thumbUrl, uploadPath);
                if (target.isAbsolute()) {
                    images.add(image);
                    choice = enterString("Bạn có muốn dùng ảnh này làm ảnh thumbnail? [y/N]: ");
                    if ("y".equalsIgnoreCase(choice)) {
                        image.setThumbnailUrl(target.getFileName().toString());
                    }
                }
                choice = enterString("Ban co muon them anh khac khong? (y/N): ");
            } while ("y".equalsIgnoreCase(choice));
            product.setImages(images);

            printTitle("Bước 3: Nhập thuộc tính cho sản phẩm");
            Set<Attribute> attributes = new HashSet<>();
            do {
                choice = enterString("Bạn muốn xem danh sách nhóm thuộc tính? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    List<AttributeGroup> attributeGroups = IterableUtils.toList(attributeGroupService.findAll());
                    if (!attributeGroups.isEmpty()) {
                        for (AttributeGroup attributeGroup : attributeGroups) {
                            System.out.println(attributeGroup.toString());
                        }
                        printTitle("Danh sách nhóm thuộc tính");
                        System.out.printf("| %-5s | %-20s |%n", "ID", "Tên");
                        for (AttributeGroup attributeGroup : attributeGroups) {
                            System.out.printf("| %-5s | %-20s |%n", attributeGroup.getId(), StringCommon.truncate(attributeGroup.getName(), 20));
                        }
                    } else {
                        System.out.println("Chưa có nhóm thuộc tính nào");
                        return;
                    }
                }
                long attributeGroupId = enterInteger("Nhập ID nhóm thuộc tính: ", true);
                Optional<AttributeGroup> attributeGroup = attributeGroupService.findById(attributeGroupId);
                if (!attributeGroup.isPresent()) {
                    System.out.println("Không tìm thấy nhóm thuộc tính nào có ID là '" + attributeGroupId + "'");
                } else {
                    Attribute attribute = new Attribute();
                    attribute.setAttributeGroup(attributeGroup.get());
                    attribute.setName(enterString("Nhập giá trị cho thuộc tính \"" + attributeGroup.get().getName() + "\":", true));
                    attributes.add(attribute);
                }
                choice = enterString("Ban co muon them thuộc tính khac khong? (y/N): ");
            } while ("y".equalsIgnoreCase(choice));
            product.setAttributes(attributes);

            Product newProduct = productService.save(product);
            if (newProduct != null) {
                System.out.println("Thêm sản phẩm mới thành công!");
            } else {
                System.out.println("Thêm sản phẩm bị lỗi!");
            }
        } catch (InputMismatchException e) {
            log.error(e.getMessage());
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
        printTitle("Danh sách Sản phẩm");
        List<Product> products = IterableUtils.toList(productService.findAll());
        if (products.isEmpty()) {
            showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
        } else {
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Tên", "Ngày tạo");
            for (Product product : products) {
                System.out.printf("| %-5s | %-20s | %-10s |%n", product.getId(), StringCommon.truncate(product.getName(), 20), StringCommon.dateFormat(product.getCreatedDate(), Constant.DATE_FORMAT));
            }
        }
    }

    @Override
    public void showOne() {
        printTitle("Xem chi tiết Sản phẩm");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách sản phẩm không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long productId = enterInteger("Nhập ID sản phẩm muốn xem: ");
            Optional<Product> product = productService.findById(productId);
            if (!product.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                printDivider();
                System.out.println("Tên: " + product.get().getName());
                System.out.println("Danh mục: " + product.get().getCategory().getName());
                System.out.println("Thương hiệu: " + product.get().getBrand().getName());
                System.out.println("Nhà cung cấp: " + product.get().getSupplier().getName());
                System.out.println("Giá: " + StringCommon.convertDoubleToVND(product.get().getUnitPrice()));
                System.out.println("% Giảm giá: " + product.get().getDiscountStr());
                System.out.println("Giá giảm: " + StringCommon.convertDoubleToVND(product.get().getCompareAtPrice()));
                System.out.println("Số lượng trong kho: " + product.get().getUnitsInStock());
                System.out.println("Số lượng đã bán: " + product.get().getUnitsOnOrder());
                System.out.println("Mô tả: " + product.get().getDescription());
                System.out.println("Thuộc tính");
                for (Attribute attribute : product.get().getAttributes()) {
                    System.out.println(attribute.getAttributeGroup().getName() + ": " + attribute.getName());
                }
                System.out.println("Thời gian tạo: " + StringCommon.dateFormat(product.get().getCreatedDate(), Constant.DATE_TIME_FORMAT));
                System.out.println("Thời gian cập nhật: " + StringCommon.dateFormat(product.get().getModifiedDate(), Constant.DATE_TIME_FORMAT));
                printDivider();
                choice = enterString("Bạn muốn xem sản phẩm khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }

    private void deleteProduct() {
        System.out.print("Bạn muốn tìm kiếm sản phẩm? [y/N]: ");
        System.out.println("Tìm thấy: '");
        System.out.println("Bạn chắc chắn muốn xóa sản phẩm ''? [y/N]: ");
        System.out.println("Vui lòng chờ...");
        System.out.println("Bạn không xóa sản phẩm ''");
        System.out.println("Xóa sản phẩm '' thành công!");
    }

    private Path copyFile(String filePath, String dir) {
        Path sourceFile = Paths.get(filePath);
        Path targetDir = Paths.get(dir);
        Path targetFile = targetDir.resolve(sourceFile.getFileName());
        try {
            Path target = Files.copy(sourceFile, targetFile);
        } catch (FileAlreadyExistsException e) {
            System.err.format("File '%s' da ton tai.\n", targetFile);
        } catch (IOException e) {
            System.err.format("Loi khi sao chep tep '%s'.\n", targetFile);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return targetFile;
    }
}
