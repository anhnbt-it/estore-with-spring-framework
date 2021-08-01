package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import vn.aptech.estore.entities.*;
import vn.aptech.estore.services.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class CategoryMenu {
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

//    @Override
//    public void run(String... args) throws Exception {
//
//        // save a few categories
//        Category category = new Category();
//        category.setName("Điện thoại");
//        category.setThumbnailUrl("dien-thoai.jpg");
//        Category category1 = categoryService.save(category);
//
//        // fetch all categories
//        log.info("Customers found with findAll():");
//        log.info("-------------------------------");
//        for (Category c : categoryService.findAll()) {
//            log.info(c.toString());
//        }
//        log.info("");
//
//        // save a few brand
//        Brand brand = new Brand();
//        brand.setName("Apple");
//        Brand brand1 = brandService.save(brand);
//
//        // fetch all brands
//        log.info("Brands found with findAll():");
//        log.info("-------------------------------");
//        for (Brand b : brandService.findAll()) {
//            log.info(b.toString());
//        }
//        log.info("");
//
//        // save a few supplier
//        Supplier supplier = new Supplier();
//        supplier.setName("Apple");
//        supplier.setThumbnailUrl("apple.jpg");
//        Supplier supplier1 = supplierService.save(supplier);
//
//        // fetch all suppliers
//        log.info("Suppliers found with findAll():");
//        log.info("-------------------------------");
//        for (Supplier s : supplierService.findAll()) {
//            log.info(s.toString());
//        }
//        log.info("");
//
//        if (!employeeService.findByUsername("anhnbt").isPresent()) {
//            // save a few supplier
//            Employee employee = new Employee();
//            employee.setFirstName("Nguyễn Bá Tuấn");
//            employee.setLastName("Anh");
//            employee.setUsername("anhnbt");
//            employee.setPassword("123456");
//            employee.setDateOfBirth(Date.valueOf("1995-11-16"));
//            employee.setGender(true);
//            employee.setEmail("anhnbt.it@gmail.com");
//            employee.setPhone("0975737642");
//            employee.setRole("ROLE_ADMIN");
//            employeeService.save(employee);
//        }
//
//        // fetch all employees
//        log.info("Employees found with findAll():");
//        log.info("-------------------------------");
//        for (Employee e : employeeService.findAll()) {
//            log.info(e.toString());
//        }
//        log.info("");
//
////        ProductMenu productMenu = new ProductMenu();
////        productMenu.start(new Scanner(System.in));
//    }


}
