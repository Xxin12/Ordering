package cn.gtapc.ordering.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.gtapc.base.entity.BaseEntity;

/**
 * 食品分类 实体类
 */
@TableName("food_type")
public class FoodType extends BaseEntity<FoodType> {

    /**
     * 名称
     */
    private String name;


    /**
     * 图标 URL
     */
    @TableField("icon_url")
    private String iconUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

}
