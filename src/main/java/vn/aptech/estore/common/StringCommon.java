package vn.aptech.estore.common;

import java.math.BigDecimal;
import java.text.*;
import java.util.Date;
import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/14/2021
 * Time: 8:33 PM
 */
public class StringCommon {

    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().equals("");
    }

    public static String convertDoubleToVND(double input) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(input) + " VND";
    }

    public static String convertBigDecimalToVND(BigDecimal input) {
        Locale.setDefault(new Locale("vi", "VN"));
        return NumberFormat.getCurrencyInstance().format(input);
    }

    public static String dateFormat(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date, String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }

    public static String truncate(String str, int length) {
        if (str.length() > (length - 3))
            str = str.substring(0, (length - 3)) + "...";
        return str;
    }
}
