package vn.aptech.estore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.*;
import vn.aptech.estore.services.*;

import java.sql.Date;

@Component
public class CategoryMenu implements CommandLineRunner {
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
    private EmployeeService employeeService;
    @Override
    public void run(String... args) throws Exception {

        // save a few categories
        Category category = new Category();
        category.setName("Điện thoại");
        category.setThumbnailUrl("dien-thoai.jpg");
        Category category1 = categoryService.save(category);

        // fetch all categories
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Category c : categoryService.findAll()) {
            log.info(c.toString());
        }
        log.info("");

        // save a few brand
        Brand brand = new Brand();
        brand.setName("Apple");
        Brand brand1 = brandService.save(brand);

        // fetch all brands
        log.info("Brands found with findAll():");
        log.info("-------------------------------");
        for (Brand b : brandService.findAll()) {
            log.info(b.toString());
        }
        log.info("");

        // save a few supplier
        Supplier supplier = new Supplier();
        supplier.setName("Apple");
        supplier.setThumbnailUrl("apple.jpg");
        Supplier supplier1 = supplierService.save(supplier);

        // fetch all suppliers
        log.info("Suppliers found with findAll():");
        log.info("-------------------------------");
        for (Supplier s : supplierService.findAll()) {
            log.info(s.toString());
        }
        log.info("");

        // save a few product
        Product product = new Product();
        product.setCategory(category1);
        product.setSupplier(supplier1);
        product.setBrand(brand1);
        product.setName("iPhone 11 64GB");
        product.setDescription("Thiết kế iPhone 11 không khác nhiều so với các thông tin được rò rỉ trước đó");
        product.setThumbnailUrl("iphone-11.jpg");
        product.setDiscount(0.0f);
        product.setQuantity(10);
        product.setStatus(true);
        product.setUnitsInStock((short) 10);
        product.setUnitsOnOrder((short) 10);
        productService.save(product);

        // fetch all products
        log.info("Products found with findAll():");
        log.info("-------------------------------");
        for (Product p : productService.findAll()) {
            log.info(p.toString());
            log.info(p.getBrand().toString());
            log.info(p.getSupplier().toString());
            log.info(p.getCategory().toString());
        }
        log.info("");

        // save a few supplier
        Employee employee = new Employee();
        employee.setName("Tuấn Anh");
        employee.setUsername("anhnbt");
        employee.setPassword("123456");
        employee.setDateOfBirth(new Date(1995, 11, 16));
        employee.setPhone("0975737642");
        Employee employee1 = employeeService.save(employee);

        // fetch all employees
        log.info("Employees found with findAll():");
        log.info("-------------------------------");
        for (Employee e : employeeService.findAll()) {
            log.info(e.toString());
        }
        log.info("");
    }
}
