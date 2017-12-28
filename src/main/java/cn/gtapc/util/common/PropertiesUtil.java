package cn.gtapc.util.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性文件工具类
 *
 * @author duanluan
 */
public final class PropertiesUtil {


    /**
     * 读取属性文件
     *
     * @param fileName classpath 下的属性文件文件名
     * @return 属性对象
     */
    public static Properties loadProperties(String fileName) {
        Properties properties = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found.");
            }
            properties = new Properties();
            properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }

    /**
     * 获取属性文件中键对应的 String 值
     *
     * @param properties 需要获取的属性文件对应的属性对象
     * @param key        键
     * @param defaultVal 键不存在时的默认值
     * @return 键对应的字符串值
     */
    public static String getStr(Properties properties, String key, String defaultVal) {
        String val = defaultVal;
        if (properties.containsKey(key)) {
            val = properties.getProperty(key);
        }
        return val;
    }

    /**
     * 获取属性文件中键对应的 String 值
     * 键不存在时的默认值为 ""
     *
     * @param properties 需要获取的属性文件对应的属性对象
     * @param key        键
     * @return 键对应的字符串值
     */
    public static String getStr(Properties properties, String key) {
        return getStr(properties, key, "");
    }

    /**
     * 获取属性文件中键对应的 int 值
     *
     * @param properties 需要获取的属性文件对应的属性对象
     * @param key        键
     * @param defaultVal 键不存在时的默认值
     * @return 键对应的字符串值
     */
    public static int getInt(Properties properties, String key, int defaultVal) {
        int val = defaultVal;
        if (properties.containsKey(key)) {
            val = ParseUtil.parseInt(properties.getProperty(key));
        }
        return val;
    }

    /**
     * 获取属性文件中键对应的 int 值
     * 键不存在时的默认值为 0
     *
     * @param properties 需要获取的属性文件对应的属性对象
     * @param key        键
     * @return 键对应的字符串值
     */
    public static int getTnt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    /**
     * 获取属性文件中键对应的 boolean 值
     *
     * @param properties 需要获取的属性文件对应的属性对象
     * @param key        键
     * @param defaultVal 键不存在时的默认值
     * @return 键对应的字符串值
     */
    public static boolean getBoolean(Properties properties, String key, boolean defaultVal) {
        boolean val = defaultVal;
        if (properties.containsKey(key)) {
            val = ParseUtil.parseBoolean(properties.getProperty(key));
        }
        return val;
    }

    /**
     * 获取属性文件中键对应的 boolean 值
     * 键不存在时的默认值为 false
     *
     * @param properties 需要获取的属性文件对应的属性对象
     * @param key        键
     * @return 键对应的字符串值
     */
    public static boolean getBoolean(Properties properties, String key) {
        return getBoolean(properties, key, false);
    }

}
