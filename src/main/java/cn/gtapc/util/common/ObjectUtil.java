package cn.gtapc.util.common;

import org.apache.commons.lang3.ObjectUtils;
import org.omg.CORBA.Object;

import java.util.ArrayList;

import static javafx.scene.input.KeyCode.T;

/**
 * Object Util
 */
public class ObjectUtil {

    /**
     * 數組及所有元素不為空
     *
     * @param values
     * @return
     */
    public static boolean allNotNull(Object... values) {
        return ObjectUtils.allNotNull(values);
    }

    /**
     * 大于零
     *
     * @param shortObj
     * @return
     */
    public static boolean greaterThanZero(Short shortObj) {
        return shortObj != null && shortObj > 0 ? true : false;
    }

    /**
     * 大于零
     *
     * @param intObj
     * @return
     */
    public static boolean greaterThanZero(Integer intObj) {
        return intObj != null && intObj > 0 ? true : false;
    }

    /**
     * 大于零
     *
     * @param longObj
     * @return
     */
    public static boolean greaterThanZero(Long longObj) {
        return longObj != null && longObj > 0 ? true : false;
    }

    /**
     * 大于零
     *
     * @param doubleObj
     * @return
     */
    public static boolean greaterThanZero(Double doubleObj) {
        return doubleObj != null && doubleObj > 0 ? true : false;
    }

    /**
     * 数组长度大于零
     *
     * @param array 需要判断的数组
     * @return
     */
    public static boolean greaterThanZero(long... array) {
        return array != null && array.length > 0 ? true : false;
    }

    /**
     * 大于等于零
     *
     * @param shortObj
     * @return
     */
    public static boolean greaterThanAndEqualZero(Short shortObj) {
        return shortObj != null && shortObj >= 0 ? true : false;
    }

    /**
     * 大于等于零
     *
     * @param intObj
     * @return
     */
    public static boolean greaterThanAndEqualZero(Integer intObj) {
        return intObj != null && intObj >= 0 ? true : false;
    }

    /**
     * 大于等于零
     *
     * @param longObj
     * @return
     */
    public static boolean greaterThanAndEqualZero(Long longObj) {
        return longObj != null && longObj >= 0 ? true : false;
    }

    /**
     * 大于等于零
     *
     * @param doubleObj
     * @return
     */
    public static boolean greaterThanAndEqualZero(Double doubleObj) {
        return doubleObj != null && doubleObj >= 0 ? true : false;
    }

    /**
     * 小于零
     *
     * @param shortObj
     * @return
     */
    public static boolean lessThanZero(Short shortObj) {
        if (shortObj != null && shortObj < 0) {
            return true;
        }
        return false;
    }

    /**
     * 小于零
     *
     * @param intObj
     * @return
     */
    public static boolean lessThanZero(Integer intObj) {
        return intObj != null && intObj < 0 ? true : false;
    }

    /**
     * 小于零
     *
     * @param longObj
     * @return
     */
    public static boolean lessThanZero(Long longObj) {
        return longObj != null && longObj < 0 ? true : false;
    }

    /**
     * 小于零
     *
     * @param doubleObj
     * @return
     */
    public static boolean lessThanZero(Double doubleObj) {
        return doubleObj != null && doubleObj < 0 ? true : false;
    }

    /**
     * 小于等于零
     *
     * @param shortObj
     * @return
     */
    public static boolean lessThanAndEqualZero(Short shortObj) {
        return shortObj != null && shortObj <= 0 ? true : false;
    }

    /**
     * 小于等于零
     *
     * @param intObj
     * @return
     */
    public static boolean lessThanAndEqualZero(Integer intObj) {
        return intObj != null && intObj <= 0 ? true : false;
    }

    /**
     * 小于等于零
     *
     * @param longObj
     * @return
     */
    public static boolean lessThanAndEqualZero(Long longObj) {
        return longObj != null && longObj <= 0 ? true : false;
    }

    /**
     * 小于等于零
     *
     * @param doubleObj
     * @return
     */
    public static boolean lessThanAndEqualZero(Double doubleObj) {
        return doubleObj != null && doubleObj <= 0 ? true : false;
    }


}
