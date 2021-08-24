package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Brand;
import vn.aptech.estore.entities.Category;
import vn.aptech.estore.services.BrandService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/15/2021
 * Time: 8:28 PM
 */
@Component
public class BrandMenu extends CRUDMenu {

    @Autowired
    private BrandService brandService;

    public BrandMenu() {
        super("Thương hiệu");
    }

    @Override
    public void create() {
        printTitle("Thêm thương hiệu");
        Brand brand = new Brand();
        brand.setName(enterString("Nhập tên: ", true));
        if (brandService.save(brand) != null) {
            showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_CREATED);
        } else {
            showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
        }
    }

    @Override
    public void update() {
        printTitle("Chỉnh sửa thương hiệu");
        do {
            String choice = enterString("Bạn có muốn xem danh sách thương hiệu không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long brandId = enterInteger("Nhập ID thương hiệu muốn chỉnh sửa: ");
            Optional<Brand> brand = brandService.findById(brandId);
            if (!brand.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                System.out.println("Tìm thấy thương hiệu " + brand.get().getName());
                brand.get().setName(enterString("Nhập tên: ", true));
                brand.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
                if (brandService.save(brand.get()) != null) {
                    showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_UPDATED);
                } else {
                    showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
                }
                choice = enterString("Bạn muốn chỉnh sửa thương hiệu khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }

    @Override
    public void delete() {
        printTitle("Xóa thương hiệu");
        String choice = enterString("Bạn có muốn xem Danh sách thương hiệu không? [y/N]: ", true);
        if ("y".equalsIgnoreCase(choice)) {
            showAll();
        }
        long brandId = enterInteger("Nhập ID thương hiệu muốn xóa: ");
        Optional<Brand> brand = brandService.findById(brandId);
        if (!brand.isPresent()) {
            System.out.println("Không tìm thấy");
        } else {
            System.out.println("Tìm thấy thương hiệu " + brand.get().getName());
            choice = enterString("Bạn có chắc chắn muốn xóa thương hiệu này? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                brandService.deleteById(brandId);
                showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_DELETED);
            } else {
                System.out.println("Bạn chọn không xóa thương hiệu " + brand.get().getName());
            }
        }
    }

    @Override
    public void showAll() {
        printTitle("Danh sách thương hiệu");
        List<Brand> brands = IterableUtils.toList(brandService.findAll());
        if (brands.isEmpty()) {
            showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
        } else {
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Tên", "Ngày tạo");
            for (Brand brand : brands) {
                System.out.printf("| %-5s | %-20s | %-10s |%n", brand.getId(), StringCommon.truncate(brand.getName(), 20), StringCommon.dateFormat(brand.getCreatedDate(), Constant.DATE_FORMAT));
            }
        }
    }

    @Override
    public void showOne() {
        printTitle("Xem chi tiết thương hiệu");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách thương hiệu không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long brandId = enterInteger("Nhập ID thương hiệu muốn xem: ");
            Optional<Brand> brand = brandService.findById(brandId);
            if (!brand.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                printDivider();
                System.out.println("Tên: " + brand.get().getName());
                System.out.println("Thời gian tạo: " + StringCommon.dateFormat(brand.get().getCreatedDate(), Constant.DATE_TIME_FORMAT));
                System.out.println("Thời gian cập nhật: " + StringCommon.dateFormat(brand.get().getModifiedDate(), Constant.DATE_TIME_FORMAT));
                printDivider();
                choice = enterString("Bạn muốn xem thương hiệu khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }
}
