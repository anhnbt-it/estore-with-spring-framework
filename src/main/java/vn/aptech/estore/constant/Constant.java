package vn.aptech.estore.constant;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/14/2021
 * Time: 6:31 PM
 */
public class Constant {
    public static enum STATUS {
        ACTIVE,
        INACTIVE
    }

    public static String DATE_TIMESTAMP_FORMAT = "HH:mm:ss.SSS dd/MM/yyyy";
    public static String DATE_TIME_FORMAT = "HH:mm:ss dd/MM/yyyy";
    public static String DATE_FORMAT = "dd/MM/yyyy";
    public static String TIME_FORMAT = "HH:mm:ss";

    public enum MESSAGE_TYPE {
        SUCCESS,
        ERROR,
        INFO
    }

    public static class Response {
        public static final String OBJECT_NOT_FOUND = "Không tìm thấy đối tượng {0}";
        public static final String OBJECT_INACTIVE = "Đối tượng {0} không ở trạng thái hoạt động";
        public static final String OBJECT_NOT_FOUND_BY_FIELD_VALUE = "Không tìm thấy đối tượng {0} với giá trị của {1} là {2}";
        public static final String OBJECT_NOT_EXISTS = "Đối tượng {0} không tồn tại";
        public static final String OBJECT_NOT_EXISTS_BY_FIELD_VALUE = "Đối tượng {0} với giá trị của {1} là {2} không tồn tại";
        public static final String OBJECT_IS_EXISTS = "Đối tượng {0} đã tồn tại";
        public static final String OBJECT_IS_EXISTS_BY_FIELD_VALUE = "Đối tượng {0} với giá trị của {1} là {2} đã tồn tại";
        public static final String MISSING_PARAM = "Thiếu dữ liệu đầu vào bắt buộc {0}";
        public static final String INVALID_REGEX = "Định dạng {0} không hợp lệ";
        public static final String OBJECT_REQUIRED = "Trường không được để trống. Vui lòng nhập lại";
        public static final String STR_REQUIRED = "Các trường bắt buộc được đánh dấu *";
        public static final String STR_CONFIRM_EXIT = "Are you sure you want to exit? [y/N]";
        public static final String STR_CONTINUE = "Do you want to continue?";
        public static final String INVALID_OPTION = "Bạn nhập sai, vui lòng nhập theo số thứ tự trên menu";
        public static final String OBJECT_CREATED = "Đối tượng {0} đã được thêm thành công";
        public static final String OBJECT_UPDATED = "Đối tượng {0} đã được cập nhật thành công";
        public static final String OBJECT_DELETED = "Đối tượng {0} đã được xóa thành công";
        public static final String SYSTEM_ERROR = "Lỗi hệ thống";
        public static final String LIST_EMPTY = "Danh sách trống";
    }


}
