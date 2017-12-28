package cn.gtapc.ordering.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum DinnerTableStatusEnum {

    /**
     * 餐桌状态
     */
    EMPTY((short) 0, "无人"),
    EATING((short) 1, "进食中");

    private short key;
    private String value;

    DinnerTableStatusEnum(short key, String value) {
        this.key = key;
        this.value = value;
    }

    public short getKey() {
        return key;
    }

    public void setKey(short key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static List<Map<String, Object>> listKeyAndValues() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        DinnerTableStatusEnum[] strs = DinnerTableStatusEnum.values();
        for (int i = 0; i < strs.length; i++) {
            map = new HashMap<>();
            map.put("key", strs[i].getKey());
            map.put("value", strs[i].getValue());
            list.add(map);
        }
        return list;
    }

}