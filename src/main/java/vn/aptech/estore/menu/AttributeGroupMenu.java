package vn.aptech.estore.menu;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.entities.AttributeGroup;
import vn.aptech.estore.entities.AttributeGroup;
import vn.aptech.estore.services.AttributeGroupService;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/26/2021
 * Time: 9:36 PM
 */
@Component
public class AttributeGroupMenu extends CRUDMenu {

    @Autowired
    private AttributeGroupService attributeGroupService;

    public AttributeGroupMenu() {
        super("Nhóm thuộc tính");
    }

    @Override
    public void create() {
        printTitle("Thêm thuộc tính");
        AttributeGroup attributeGroup = new AttributeGroup();
        attributeGroup.setName(enterString("Nhập tên: ", true));
        if (attributeGroupService.save(attributeGroup) != null) {
            showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_CREATED);
        } else {
            showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
        }
    }

    @Override
    public void update() {
        printTitle("Chỉnh sửa thuộc tính");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách thuộc tính không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long attributeGroupId = enterInteger("Nhập ID thuộc tính muốn chỉnh sửa: ");
            Optional<AttributeGroup> attributeGroup = attributeGroupService.findById(attributeGroupId);
            if (!attributeGroup.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                System.out.println("Tìm thấy thuộc tính " + attributeGroup.get().getName());
                attributeGroup.get().setName(enterString("Nhập tên: ", true));
                attributeGroup.get().setModifiedDate(new Timestamp(System.currentTimeMillis()));
                if (attributeGroupService.save(attributeGroup.get()) != null) {
                    showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_UPDATED);
                } else {
                    showMsg(Constant.MESSAGE_TYPE.ERROR, Constant.Response.SYSTEM_ERROR);
                }
                choice = enterString("Bạn muốn chỉnh sửa thuộc tính khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }

    @Override
    public void delete() {
        printTitle("Xóa thuộc tính");
        String choice = enterString("Bạn có muốn xem Danh sách thuộc tính không? [y/N]: ", true);
        if ("y".equalsIgnoreCase(choice)) {
            showAll();
        }
        long attributeGroupId = enterInteger("Nhập ID thuộc tính muốn xóa: ");
        Optional<AttributeGroup> attributeGroup = attributeGroupService.findById(attributeGroupId);
        if (!attributeGroup.isPresent()) {
            System.out.println("Không tìm thấy");
        } else {
            System.out.println("Tìm thấy thuộc tính " + attributeGroup.get().getName());
            choice = enterString("Bạn có chắc chắn muốn xóa thuộc tính này? [y/N]: ");
            if ("y".equalsIgnoreCase(choice)) {
                attributeGroupService.deleteById(attributeGroupId);
                showMsg(Constant.MESSAGE_TYPE.SUCCESS, Constant.Response.OBJECT_DELETED);
            } else {
                System.out.println("Bạn chọn không xóa thuộc tính " + attributeGroup.get().getName());
            }
        }
    }

    @Override
    public void showAll() {
        printTitle("Danh sách Thuộc tính");
        List<AttributeGroup> categories = IterableUtils.toList(attributeGroupService.findAll());
        if (categories.isEmpty()) {
            showMsg(Constant.MESSAGE_TYPE.INFO, Constant.Response.LIST_EMPTY);
        } else {
            System.out.printf("| %-5s | %-20s | %-10s |%n", "ID", "Tên", "Ngày tạo");
            for (AttributeGroup attributeGroup : categories) {
                System.out.printf("| %-5s | %-20s | %-10s |%n", attributeGroup.getId(), StringCommon.truncate(attributeGroup.getName(), 20), StringCommon.dateFormat(attributeGroup.getCreatedDate(), Constant.DATE_FORMAT));
            }
        }
    }

    @Override
    public void showOne() {
        printTitle("Xem chi tiết Thuộc tính");
        do {
            String choice = enterString("Bạn có muốn xem Danh sách thuộc tính không? [y/N]: ", true);
            if ("y".equalsIgnoreCase(choice)) {
                showAll();
            }
            long attributeGroupId = enterInteger("Nhập ID thuộc tính muốn xem: ");
            Optional<AttributeGroup> attributeGroup = attributeGroupService.findById(attributeGroupId);
            if (!attributeGroup.isPresent()) {
                System.out.println("Không tìm thấy");
            } else {
                printDivider();
                System.out.println("Tên: " + attributeGroup.get().getName());
                System.out.println("Thời gian tạo: " + StringCommon.dateFormat(attributeGroup.get().getCreatedDate(), Constant.DATE_TIME_FORMAT));
                System.out.println("Thời gian cập nhật: " + StringCommon.dateFormat(attributeGroup.get().getModifiedDate(), Constant.DATE_TIME_FORMAT));
                printDivider();
                choice = enterString("Bạn muốn xem thuộc tính khác không? [y/N]: ");
                if (!"y".equalsIgnoreCase(choice)) {
                    break;
                }
            }
        } while (true);
    }
}
