package vn.aptech.estore.validate;

import vn.aptech.estore.common.StringCommon;
import vn.aptech.estore.constant.Constant;
import vn.aptech.estore.exception.CommonException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/27/2021
 * Time: 6:55 AM
 */
public class ValidateCommon {

    public static void validateNullObject(Object object) {
        if (object instanceof String) {
            if (StringCommon.isNullOrBlank(String.valueOf(object)))
                throw new CommonException(Constant.Response.MISSING_PARAM);
        } else if (Objects.isNull(object))
            throw new CommonException(Constant.Response.MISSING_PARAM);
    }

    public static boolean validateUsername(String name) {
        validateNullObject(name);
        String regex = "^[a-z]\\w{5,29}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(name);
        return !m.matches();
    }

    public static boolean validatePassword(String password) {
        validateNullObject(password);
        String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{6,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return !m.matches();
    }
}
