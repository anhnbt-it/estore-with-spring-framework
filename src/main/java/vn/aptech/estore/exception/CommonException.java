package vn.aptech.estore.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: Nguyen Ba Tuan Anh <anhnbt.it@gmail.com>
 * Date: 8/27/2021
 * Time: 6:57 AM
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CommonException extends RuntimeException {
    private String message;

    public CommonException(String message) {
        super(message);
        this.message = message;
    }
}
