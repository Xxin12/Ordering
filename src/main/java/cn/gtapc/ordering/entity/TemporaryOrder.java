package cn.gtapc.ordering.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.gtapc.base.entity.BaseEntity;
/**
 * 临时订单 实体类
 */
@TableName("temporary_order")
public class TemporaryOrder extends BaseEntity<TemporaryOrder> {

	@TableField("order_id")
	private Long orderId;

    /**
     * 食品 ID
     */
	@TableField("food_id")
	private Long foodId;
    /**
     * 点菜的数量
     */
	private Integer number;
    /**
     * 总价 = 食品价格 * 数量
     */
	@TableField("total_price")
	private Long totalPrice;
	/**
	 * 临时订单完成状态
	 */
	private boolean finished;

//	/**
//	 * 食品名称
//	 */
//	@TableField("name")
//	private String name;

	/**
	 * 临时订单完成时间
	 */
	@TableField("finished_time")
	private Date finishedTime;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public Date getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	public Long getFoodId() {
		return foodId;
	}

	public void setFoodId(Long foodId) {
		this.foodId = foodId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

}
