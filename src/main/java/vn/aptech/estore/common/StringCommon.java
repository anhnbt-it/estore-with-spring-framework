package vn.aptech.estore.common;

import vn.aptech.estore.constant.Constant;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public static String dateFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
        return dateFormat.format(date);
    }
}
