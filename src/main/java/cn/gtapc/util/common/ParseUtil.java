package cn.gtapc.util.common;

/**
 * 类型转换工具类
 *
 * @author duanluan
 */
public final class ParseUtil {

    /**
     * 转换为 String 类型的对象
     *
     * @param obj        需要转换类型的对象
     * @param defaultVal 需要转换类型的对象为 null 时的默认值
     * @return 转换为 String 类型后的对象
     */
    public static String parseString(Object obj, String defaultVal) {
        return obj != null ? String.valueOf(obj) : defaultVal;
    }

    /**
     * 转换为 String 类型
     * 需要转换类型的对象为 null 时默认值为 ""
     *
     * @param obj 需要转换类型的对象
     * @return 转换为 String 类型后的对象
     */
    public static String parseString(Object obj) {
        return parseString(obj, "");
    }

    /**
     * 转换为 double 类型的对象
     *
     * @param obj        需要转换类型的对象
     * @param defaultVal 需要转换类型的对象为 null 或者转换失败时的默认值
     * @return 转换为 double 类型后的对象
     */
    public static double parseDouble(Object obj, double defaultVal) {
        double doubleVal = defaultVal;
        if (obj != null) {
            String strVal = parseString(obj);
            if (StringUtil.isNotEmpty(strVal)) {
                try {
                    doubleVal = Double.parseDouble(strVal);
                } catch (NumberFormatException e) {
                    doubleVal = defaultVal;
                }
            }
        }
        return doubleVal;
    }

    /**
     * 转换为 double 类型的对象
     * 需要转换类型的对象为 null 或者转换失败时默认值为 0
     *
     * @param obj 需要转换类型的对象
     * @return 转换为 double 类型后的对象
     */
    public static double parseDouble(Object obj) {
        return parseDouble(obj, 0);
    }

    /**
     * 转换为 long 类型的对象
     *
     * @param obj        需要转换类型的对象
     * @param defaultVal 需要转换类型的对象为 null 或者转换失败时的默认值
     * @return 转换为 long 类型后的对象
     */
    public static long parseLong(Object obj, long defaultVal) {
        long longVal = defaultVal;
        if (obj != null) {
            String strVal = parseString(obj);
            if (StringUtil.isNotEmpty(strVal)) {
                try {
                    longVal = Long.parseLong(strVal);
                } catch (NumberFormatException e) {
                    longVal = defaultVal;
                }
            }
        }
        return longVal;
    }

    /**
     * 转换为 long 类型的对象
     * 需要转换类型的对象为 null 或者转换失败时默认值为 0
     *
     * @param obj 需要转换类型的对象
     * @return 转换为 long 类型后的对象
     */
    public static long parseLong(Object obj) {
        return parseLong(obj, 0);
    }

    /**
     * 转换为 int 类型的对象
     *
     * @param obj        需要转换类型的对象
     * @param defaultVal 需要转换类型的对象为 null 或者转换失败时的默认值
     * @return 转换为 int 类型后的对象
     */
    public static int parseInt(Object obj, int defaultVal) {
        int intVal = defaultVal;
        if (obj != null) {
            String strVal = parseString(obj);
            if (StringUtil.isNotEmpty(strVal)) {
                try {
                    intVal = Integer.parseInt(strVal);
                } catch (NumberFormatException e) {
                    intVal = defaultVal;
                }
            }
        }
        return intVal;
    }

    /**
     * 转换为 int 类型的对象
     * 需要转换类型的对象为 null 或者转换失败时默认值为 0
     *
     * @param obj 需要转换类型的对象
     * @return 转换为 int 类型后的对象
     */
    public static int parseInt(Object obj) {
        return parseInt(obj, 0);
    }

    /**
     * 转换为 short 类型的对象
     *
     * @param obj        需要转换类型的对象
     * @param defaultVal 需要转换类型的对象为 null 或者转换失败时的默认值
     * @return 转换为 short 类型后的对象
     */
    public static short parseShort(Object obj, short defaultVal) {
        short shortVal = defaultVal;
        if (obj != null) {
            String strVal = parseString(obj);
            if (StringUtil.isNotEmpty(strVal)) {
                try {
                    shortVal = Short.parseShort(strVal);
                } catch (NumberFormatException e) {
                    shortVal = defaultVal;
                }
            }
        }
        return shortVal;
    }

    /**
     * 转换为 short 类型的对象
     * 需要转换类型的对象为 null 或者转换失败时默认值为 0
     *
     * @param obj 需要转换类型的对象
     * @return 转换为 short 类型后的对象
     */
    public static short parseShort(Object obj) {
        return parseShort(obj, (short) 0);
    }

    /**
     * 转换为 boolean 类型的对象
     *
     * @param obj        需要转换类型的对象
     * @param defaultVal 需要转换类型的对象为 null 时的默认值
     * @return 转换为 boolean 类型后的对象
     */
    public static boolean parseBoolean(Object obj, boolean defaultVal) {
        boolean booleanVal = defaultVal;
        if (obj != null) {
            booleanVal = Boolean.parseBoolean(parseString(obj));
        }
        return booleanVal;
    }

    /**
     * 转换为 boolean 类型的对象
     * 需要转换类型的对象为 null 时默认值为 false
     *
     * @param obj 需要转换类型的对象
     * @return 转换为 boolean 类型后的对象
     */
    public static boolean parseBoolean(Object obj) {
        return parseBoolean(obj, false);
    }

}
