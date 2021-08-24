package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Supplier;
import vn.aptech.estore.services.SupplierService;

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
public class SupplierMenu extends CRUDMenu {

    @Autowired
    private SupplierService supplierService;

    public SupplierMenu() {
        super("Nhà cung cấp");
    }

    @Override
    public void create() {
        printTitle("Thêm nhà cung cấp");
        Supplier supplier = new Supplier();
        supplier.setName(enterString("Nhập tên: ", true));
        if (supplierService.save(supplier) != null) {
            showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_CREATED);
        } else {
            showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
        }
    }

    @Override
    public void update() {
        printTitle("Chỉnh sửa nhà cung cấp");
        do {
            String choice = enterString("Bạn có muốn xem danh sách nhà cung cấp không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long supplierId = enterInteger("Nhập ID nhà cung cấp muốn chỉnh sửa: ");
            Optional<Supplier> supplier = supplierService.findById(supplierId);
            if (!supplier.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                System.out.println("Tìm thấy nhà cung cấp " + supplier.get().getName());
                supplier.get().setName(enterString("Nhập tên: ", true));
                supplier.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
                if (supplierService.save(supplier.get()) != null) {
                    showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_UPDATED);
                } else {
                    showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
                }
                choice = enterString("Bạn muốn chỉnh sửa nhà cung cấp khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }

    @Override
    public void delete() {
        printTitle("Xóa nhà cung cấp");
        String choice = enterString("Bạn có muốn xem Danh sách nhà cung cấp không? [y/N]: ", true);
        if ("y".equalsIgnoreCase(choice)) {
            showAll();
        }
        long supplierId = enterInteger("Nhập ID nhà cung cấp muốn xóa: ");
        Optional<Supplier> supplier = supplierService.findById(supplierId);
        if (!supplier.isPresent()) {
            System.out.println("Không tìm thấy");
        } else {
            System.out.println("Tìm thấy nhà cung cấp " + supplier.get().getName());
            choice = enterString("Bạn có chắc chắn muốn xóa nhà cung cấp này? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                supplierService.deleteById(supplierId);
                showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_DELETED);
            } else {
                System.out.println("Bạn chọn không xóa nhà cung cấp " + supplier.get().getName());
            }
        }

    }

    @Override
    public void showAll() {
        printTitle("Danh sách nhà cung cấp");
        List<Supplier> suppliers = IterableUtils.toList(supplierService.findAll());
        if (suppliers.isEmpty()) {
            showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
        } else {
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Tên", "Ngày tạo");
            for (Supplier supplier : suppliers) {
                System.out.printf("| %-5s | %-20s | %-10s |%n", supplier.getId(), StringCommon.truncate(supplier.getName(), 20), StringCommon.dateFormat(supplier.getCreatedDate(), Constant.DATE_FORMAT));
            }
        }

    }

    @Override
    public void showOne() {
        printTitle("Xem chi tiết nhà cung cấp");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách nhà cung cấp không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long supplierId = enterInteger("Nhập ID nhà cung cấp muốn xem: ");
            Optional<Supplier> supplier = supplierService.findById(supplierId);
            if (!supplier.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                printDivider();
                System.out.println("Tên: " + supplier.get().getName());
                System.out.println("Thời gian tạo: " + StringCommon.dateFormat(supplier.get().getCreatedDate(), Constant.DATE_TIME_FORMAT));
                System.out.println("Thời gian cập nhật: " + StringCommon.dateFormat(supplier.get().getModifiedDate(), Constant.DATE_TIME_FORMAT));
                printDivider();
                choice = enterString("Bạn muốn xem nhà cung cấp khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }
}
