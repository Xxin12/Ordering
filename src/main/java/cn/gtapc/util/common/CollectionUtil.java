package cn.gtapc.util.common;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * 类集工具类
 *
 * @author duanluan
 */
public final class CollectionUtil {

    /**
     * 判断类集是否为空
     *
     * @param collection 需要判断的类集
     * @return 是否为空的结果
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断类集是否不为空
     *
     * @param collection 需要判断的集合
     * @return 是否不为空的结果
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    /**
     * 判斷類集是否不為空且只有一個元素
     *
     * @param collection 需要判断的集合
     * @return 是否不為空且只有一個元素的結果
     */
    public static boolean isNotEmptyAndOnlyOne(Collection<?> collection) {
        return CollectionUtil.isNotEmpty(collection) && collection.size() == 1;
    }

    /**
     * 判断集合长度是否大于零
     *
     * @param collection 需要判断的集合
     * @return
     */
    public static boolean greaterThanZero(Collection<?> collection) {
        return collection != null && collection.size() > 0 ? true : false;
    }

}
