package cn.gtapc.util.common;

import java.util.Random;

public class RandomUtil {

    public static final String ALL = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String UPPERCASE_LOWERCASE_LETTER = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String UPPERCASE_LETTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWERCASE_LETTER = "abcdefghijkllmnopqrstuvwxyz";
    public static final String NUMBER = "0123456789";

    /**
     * 根據源字符生成指定長度的隨機字符串
     *
     * @param sourceStr
     * @param length
     * @return
     */
    public static String generateStr(String sourceStr, Long length){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(sourceStr.charAt(random.nextInt(sourceStr.length())));
        }

        return stringBuffer.toString();
    }

}
