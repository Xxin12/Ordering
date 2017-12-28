package cn.gtapc.ordering.entity.vo;

import cn.gtapc.ordering.entity.TemporaryOrder;
import com.baomidou.mybatisplus.annotations.TableField;

import java.util.Date;
import java.util.List;

public class OrderedVO {

    private Long totalPrice;
    private Long dinnerTableId;
    private boolean finished;
    private Date finishedTime;

    private List<TemporaryOrderVO> temporaryOrderVOList;

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getDinnerTableId() {
        return dinnerTableId;
    }

    public void setDinnerTableId(Long dinnerTableId) {
        this.dinnerTableId = dinnerTableId;
    }

    public boolean isFinished() {
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

    public List<TemporaryOrderVO> getTemporaryOrderVOList() {
        return temporaryOrderVOList;
    }

    public void setTemporaryOrderVOList(List<TemporaryOrderVO> temporaryOrderVOList) {
        this.temporaryOrderVOList = temporaryOrderVOList;
    }
}
