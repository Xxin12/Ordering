package cn.gtapc.ordering.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import cn.gtapc.base.entity.BaseEntity;
/**
 * 订单 实体类
 */
@TableName("_order")
public class Order extends BaseEntity<Order> {

//	/**
//     * 临时订单 ID
//     */
//	@TableField("temporary_order_ids")
//	private String temporaryOrderIds;
    /**
     * 总价 += 临时订单总价
     */
	@TableField("total_price")
	private Long totalPrice;

	@TableField("dinner_table_id")
	private Long dinnerTableId;

	/**
	 * 完成状态
	 */
	private boolean finished;

	/**
	 * 完成时间
	 */
	@TableField("finished_time")
	private Date finishedTime;

	public Long getDinnerTableId() {
		return dinnerTableId;
	}

	public void setDinnerTableId(Long dinnerTableId) {
		this.dinnerTableId = dinnerTableId;
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

//	public String getTemporaryOrderIds() {
//		return temporaryOrderIds;
//	}
//
//	public void setTemporaryOrderIds(String temporaryOrderIds) {
//		this.temporaryOrderIds = temporaryOrderIds;
//	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
}
