package cn.gtapc.ordering.constant;

import com.baomidou.mybatisplus.plugins.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum RequestResultEnum {

    /**
     *消息响应
     */
    SUCCESS(0x000000, "成功"),
    PARAM_ERROR(0x000101, "参数错误"),

    DINNER_TABLE_EXIST(0x010001,"餐桌已存在"),
    DINNER_TABLE_NOT_EXIST(0x010002,"餐桌不存在"),
    DINNER_TABLE_STATUS_IS_WRONG(0x010101,"餐桌状态不正确"),
    FOOD_TYPE_EXIST(0x020001,"食品分类已存在"),
    FOOD_TYPE_NOT_EXIST(0x020002,"食品分类不存在"),
    FOOD_EXIST(0x030001,"食品已存在"),
    FOOD_NOT_EXIST(0x030002,"食品不存在"),
    TEMPORARY_ORDER_EXIST(0x040001,"临时订单已存在"),
    TEMPORARY_ORDER_NOT_EXIST(0x040002,"临时订单不存在"),
    TEMPORARY_ORDER_FINISHED(0x040101,"临时订单已完成"),
    TEMPORARY_ORDER_NOT_FINISHED(0x040102,"临时订单未完成"),


    FAILURE(0x000001, "失败");

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    RequestResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public Map<String, Object> getResult() {
        Map<String, Object> map = new HashMap<>();
        map.put("message", this.getMessage());
        map.put("code", this.code);

        return map;
    }

    public Map<String, Object> getResult(Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", this.getMessage());
        map.put("code", this.code);
        map.put("data", data);
        return map;
    }

    public Map<String, Object> getPageResult(Page page, Object data) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", this.getMessage());
        map.put("code", this.code);
        map.put("data", data);
        map.put("total", page.getTotal());
        return map;
    }

}
