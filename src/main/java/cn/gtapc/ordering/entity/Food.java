package cn.gtapc.ordering.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import cn.gtapc.base.entity.BaseEntity;
/**
 * 食品 实体类
 */
public class Food extends BaseEntity<Food> {

    /**
     * 名称
     */
	private String name;
	/**
	 * 价格
	 */
	private Long price;
    /**
     * 图片 URL
     */
	@TableField("image_url")
	private String imageUrl;
    /**
     * 食物分类 ID
     */
	@TableField("food_type_id")
	private Long foodTypeId;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Long getFoodTypeId() {
		return foodTypeId;
	}

	public void setFoodTypeId(Long foodTypeId) {
		this.foodTypeId = foodTypeId;
	}

}
