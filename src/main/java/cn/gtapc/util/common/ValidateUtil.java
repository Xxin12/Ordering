package cn.gtapc.util.common;

import java.util.regex.Pattern;

public class ValidateUtil {

    private ValidateUtil() {
    }

    public static boolean validateEmail(String email) {
        return Pattern.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", email);
    }

    /**
     * 6 - 20 位的英文、數字或半角符號
     *
     * @param password
     * @return
     */
    public static boolean validatePassword(String password) {
        return Pattern.matches("^[a-zA-Z0-9\\x21-\\x7e]{5,19}$", password);
    }


}
