package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.Role;
import vn.aptech.estore.entities.User;
import vn.aptech.estore.services.UserService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/15/2021
 * Time: 8:29 PM
 */
@Component
public class EmployeeMenu extends CRUDMenu {

    @Autowired
    private UserService userService;

    public EmployeeMenu() {
        super("Nhân viên");
    }

    @Override
    public void create() {
        printTitle("Thêm nhân viên");
        User user = new User();
        user.setUsername(enterString("Nhập tên đăng nhập: ", true));
        user.setPassword(enterString("Nhập mật khẩu: ", true));
        user.setEmail(enterString("Nhập email: ", true));
        user.setRole(Role.ROLE_STAFF);
        if (userService.save(user) != null) {
            showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_CREATED);
        } else {
            showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
        }
    }

    @Override
    public void update() throws ParseException {
        printTitle("Chỉnh sửa nhân viên");
        do {
            String choice = enterString("Bạn có muốn xem danh sách nhân viên không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long userId = enterInteger("Nhập ID nhân viên muốn chỉnh sửa: ");
            Optional<User> user = userService.findById(userId);
            if (!user.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                System.out.println("Tìm thấy nhân viên " + user.get().getFullName());
                user.get().setFirstName(enterString("Nhập họ: ", true));
                user.get().setLastName(enterString("Nhập tên: ", true));
                user.get().setEmail(enterString("Nhập email: ", true));
                user.get().setPhone(enterString("Nhập số điện thoại: ", true));
                user.get().setGender(enterBoolean("Chọn giới tính [true=Nam;false=Nữ]: "));
                user.get().setDateOfBirth(enterDate("Nhập năm/tháng/ngày sinh: "));
                user.get().setRole(enterRole("Nhập vai trò [ROLE_ADMIN, ROLE_STAFF]: "));
                user.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
                if (userService.save(user.get()) != null) {
                    showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_UPDATED);
                } else {
                    showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
                }
                choice = enterString("Bạn muốn chỉnh sửa nhân viên khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }

    @Override
    public void delete() {
        printTitle("Xóa nhân viên");
        String choice = enterString("Bạn có muốn xem Danh sách nhân viên không? [y/N]: ", true);
        if ("y".equalsIgnoreCase(choice)) {
            showAll();
        }
        long userId = enterInteger("Nhập ID nhân viên muốn xóa: ");
        Optional<User> user = userService.findById(userId);
        if (!user.isPresent()) {
            System.out.println("Không tìm thấy");
        } else {
            System.out.println("Tìm thấy nhân viên " + user.get().getFullName());
            choice = enterString("Bạn có chắc chắn muốn xóa nhân viên này? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                userService.deleteById(userId);
                showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_DELETED);
            } else {
                System.out.println("Bạn chọn không xóa nhân viên " + user.get().getFullName());
            }
        }
    }

    @Override
    public void showAll() {
        printTitle("Danh sách nhân viên");
        List<User> users = IterableUtils.toList(userService.findAll());
        if (users.isEmpty()) {
            showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
        } else {
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Tên", "Ngày tạo");
            for (User user : users) {
                System.out.printf("| %-5s | %-20s | %-10s |%n", user.getId(), StringCommon.truncate(user.getFullName(), 20), StringCommon.dateFormat(user.getCreatedDate(), Constant.DATE_FORMAT));
            }
        }
    }

    @Override
    public void showOne() {
        printTitle("Xem chi tiết nhân viên");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách nhân viên không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long userId = enterInteger("Nhập ID nhân viên muốn xem: ");
            Optional<User> user = userService.findById(userId);
            if (!user.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                printDivider();
                System.out.println("Tên: " + user.get().getFullName());
                System.out.println("Thời gian tạo: " + StringCommon.dateFormat(user.get().getCreatedDate(), Constant.DATE_TIME_FORMAT));
                System.out.println("Thời gian cập nhật: " + StringCommon.dateFormat(user.get().getModifiedDate(), Constant.DATE_TIME_FORMAT));
                printDivider();
                choice = enterString("Bạn muốn xem nhân viên khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }
}
