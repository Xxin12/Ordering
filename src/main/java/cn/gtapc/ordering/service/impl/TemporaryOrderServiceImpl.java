package cn.gtapc.ordering.service.impl;

import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.entity.vo.OrderedVO;
import cn.gtapc.ordering.entity.vo.TemporaryOrderVO;
import cn.gtapc.ordering.mapper.FoodMapper;
import cn.gtapc.ordering.mapper.OrderMapper;
import cn.gtapc.ordering.mapper.TemporaryOrderMapper;
import cn.gtapc.ordering.service.ITemporaryOrderService;
import cn.gtapc.util.common.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 临时订单 Service 实现
 * </p>
 */
@Service
public class TemporaryOrderServiceImpl extends ServiceImpl<TemporaryOrderMapper, TemporaryOrder> implements ITemporaryOrderService {

    @Autowired
    private TemporaryOrderMapper temporaryOrderMapper;

    /**
     * 获取分页数据
     *
     * @param page
     * @return
     */
    @Override
    public List<TemporaryOrderVO> list(Page page, Boolean temporaryOrderFinished) {
        return temporaryOrderMapper.selectVO(page, temporaryOrderFinished);
    }

    @Override
    public List<TemporaryOrder> list(long dinnerTableId) {
        // 根据餐桌 ID 获取未完成订单所属的未完成的临时订单 ID
        return temporaryOrderMapper.selectByDinnerTableId(dinnerTableId);
    }

    @Override
    public boolean update(long[] temporaryOrder2) throws Exception {
        try {
            for (int i = 0; i < temporaryOrder2.length; i++) {
                long id = temporaryOrder2[i];
                TemporaryOrder temporaryOrder = this.selectById(id);
                if (temporaryOrder.getNumber() != 0 && temporaryOrder.getNumber() != null) {
                    temporaryOrder.setFinished(true);
                    temporaryOrder.setUpdatedTime(new Date());
                    // 如果需要删除不大于零 执行回滚操作
                    if (!ObjectUtil.greaterThanZero(temporaryOrderMapper.updateById(temporaryOrder))) {
                        TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                        return false;
                    }
                }

            }
        } catch (Exception e) {

        }
        return true;

    }


    @Override
    public boolean delete(long[] deleted) {
        try {
            for (int i = 0; i < deleted.length; i++) {
                long id = deleted[i];
                TemporaryOrder temporaryOrder = this.selectById(id);
                temporaryOrder.setDeleted(true);
                temporaryOrder.setUpdatedTime(new Date());
                // 如果需要删除不大于零 执行回滚操作
                if (!ObjectUtil.greaterThanZero(temporaryOrderMapper.updateById(temporaryOrder))) {
                    TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                    return false;
                }


            }
        } catch (Exception e) {

        }
        return true;

    }

}
