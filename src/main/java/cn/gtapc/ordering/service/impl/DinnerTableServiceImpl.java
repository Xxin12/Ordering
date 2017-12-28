package cn.gtapc.ordering.service.impl;

import cn.gtapc.ordering.constant.DinnerTableStatusEnum;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.service.IDinnerTableService;
import cn.gtapc.util.common.ObjectUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import cn.gtapc.ordering.mapper.DinnerTableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
/**
 * <p>
 * 餐桌 Service 实现
 * </p>
 *
 */
@Service
public class DinnerTableServiceImpl extends ServiceImpl<DinnerTableMapper, DinnerTable> implements IDinnerTableService {

    @Autowired
    private DinnerTableMapper dinnerTableMapper;

    /**
     * 获取分页数据
     * @param page
     * @return
     */
    @Override
    public List<DinnerTable> list(Page page) {
        return dinnerTableMapper.select(page);
    }
    /**
     * 删除多条数据
     *
     * @param ids 需要删除的数据 ID 数组
     * @return
     */
    @Transactional
    @Override
    public boolean delete(long[] ids) throws Exception {
        // 循环删除
        for (int i = 0; i < ids.length; i++) {
            long id = ids[i];
            //
            DinnerTable dinnerTable = this.selectById(id);
            dinnerTable.setDeleted(true);
            dinnerTable.setUpdatedTime(new Date());
            // 如果需要删除的餐桌id不大于零 执行回滚操作
            if (!ObjectUtil.greaterThanZero(dinnerTableMapper.updateById(dinnerTable))) {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                return false;
            }
        }
        return true;
    }

    /**
     * 修改多条数据
     *
     * @param ids 需要修改的数据 ID 数组
     * @return
     * @throws Exception
     */
    @Transactional
    @Override
    public boolean update(long[] ids) throws Exception {
        // 循环修改状态
        for (int i = 0; i < ids.length; i++) {
            long id = ids[i];
            DinnerTable dinnerTable = new DinnerTable();
            dinnerTable.setId(id);
            dinnerTable.setDeleted(false);
            dinnerTable = dinnerTableMapper.selectById(dinnerTable);
            // 如果 餐桌状态 无人 就修改成 进食中
            if (DinnerTableStatusEnum.EMPTY.getKey() == dinnerTable.getStatus()) {
                dinnerTable.setStatus(DinnerTableStatusEnum.EATING.getKey());
            } else {
                // 否则修改成 无人 状态
                dinnerTable.setStatus(DinnerTableStatusEnum.EMPTY.getKey());
            }
            // 写入更新时间
            dinnerTable.setUpdatedTime(new Date());
            // 如果需要删除的餐桌id不大于零 执行回滚操作
            if(!ObjectUtil.greaterThanZero(dinnerTableMapper.updateById(dinnerTable))){
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                return false;
            }
        }
        return true;
    }
}
