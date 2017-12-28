package cn.gtapc.ordering.entity;

import cn.gtapc.base.entity.BaseEntity;

/**
 * 餐桌 实体类
 */
public class DinnerTable extends BaseEntity<DinnerTable> {

    /**
     * 餐桌状态
     */
    private Short status;
    public Short getStatus() {
        return status;
    }
    public void setStatus(Short status) {
        this.status = status;
    }
}