package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
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

    public ProductMenu() {
        super("Sản phẩm");
    }

    @Override
    public void create() {
        try {
            printTitle("Bước 1: Thông tin cơ bản");
            Product product = new Product();
            String choice = enterString("Bạn muốn xem danh sách danh mục? [y/N]: ");
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
                    for (Supplier supplier : suppliers) {
                        System.out.println(supplier.toString());
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
                    for (Brand brand : brands) {
                        System.out.println(brand.toString());
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
            product.setThumbnailUrl("Nhập ảnh: ");
            product.setUnitPrice(enterDouble("Nhập giá: ", true));
            product.setCompareAtPrice(product.getUnitPrice() - product.getUnitPrice() * product.getDiscountPercent());
            System.out.println("Nhập % giảm giá (Ví dụ: 0.1 tương đương 10%): ");
            product.setDiscountPercent(scanner.nextFloat());

            System.out.println("Nhập số lượng: ");
            product.setUnitsInStock(scanner.nextInt());

            System.out.println("Nhập trạng thái [true - Hiển thị; false - Ẩn]: ");
            product.setStatus(scanner.nextBoolean());

            printTitle("Bước 2: Ảnh sản phẩm");
            List<Image> images = new ArrayList<>();
            do {
                Image image = new Image();
                String thumbUrl = enterString("Nhập đường đến tập tin ảnh trong máy: ");
                Path target = copyFile(thumbUrl, "C:\\projects\\hanoi-aptech\\SEM 2\\estore-with-spring-framework\\src\\main\\resources\\images");
                if (target.isAbsolute()) {
                    image.setThumbnailUrl(target.getFileName().toString());
                    images.add(image);
                }
                choice = enterString("Ban co muon them anh khac khong? (y/N): ");
            } while ("y".equalsIgnoreCase(choice));
            product.setImages(images);

            printTitle("Bước 2: Thuộc tính của sản phẩm");
            Set<Attribute> attributes = new HashSet<>();
            do {
                choice = enterString("Bạn muốn xem danh sách thuộc tính? [y/N]: ");
                if ("y".equalsIgnoreCase(choice)) {
                    List<AttributeGroup> attributeGroups = IterableUtils.toList(attributeGroupService.findAll());
                    if (!attributeGroups.isEmpty()) {
                        for (AttributeGroup attributeGroup : attributeGroups) {
                            System.out.println(attributeGroup.toString());
                        }
                    } else {
                        System.out.println("Chưa có thuộc tính nào");
                        return;
                    }
                }
                long attributeGroupId = enterInteger("Nhập ID thuộc tính: ", true);
                Optional<AttributeGroup> attributeGroup = attributeGroupService.findById(attributeGroupId);
                if (!attributeGroup.isPresent()) {
                    System.out.println("Không tìm thấy thuộc tính nào có ID là '" + brandId + "'");
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
