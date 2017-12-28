package cn.gtapc.util.common;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 *
 * @author duanluan
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     *
     * @param str 需要判断的字符串
     * @return 是否为空的结果
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 需要判断的字符串
     * @return 是否为空的结果
     */
    public static boolean isEmpty(Object str) {
        if (str != null) {
            str = str.toString().trim();
            return StringUtils.isEmpty(str.toString());
        } else {
            return false;
        }
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 需要判断的字符串
     * @return 是否不为空的结果
     */
    public static boolean isNotEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isNotEmpty(str);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 需要判断的字符串
     * @return 是否不为空的结果
     */
    public static boolean isNotEmpty(Object str) {
        if (str != null) {
            str = str.toString().trim();
            return StringUtils.isNotEmpty(str.toString());
        } else {
            return false;
        }
    }

}
