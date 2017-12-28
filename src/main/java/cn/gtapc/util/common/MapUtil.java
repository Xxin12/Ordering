package cn.gtapc.util.common;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;

/**
 * 集合工具类
 *
 * @author duanluan
 */
public final class MapUtil {

    /**
     * 判断集合是否为空
     *
     * @param map 需要判断的集合
     * @return 是否为空的结果
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断集合是否不为空
     *
     * @param map 需要判断的集合
     * @return 是否不为空的结果
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return MapUtils.isNotEmpty(map);
    }

}
