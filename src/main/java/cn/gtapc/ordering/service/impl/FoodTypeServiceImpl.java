package cn.gtapc.ordering.service.impl;

import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.mapper.FoodMapper;
import cn.gtapc.ordering.mapper.FoodTypeMapper;
import cn.gtapc.ordering.service.IFoodTypeService;
import cn.gtapc.util.common.ObjectUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
/**
 * <p>
 * 食品分类 Service 实现
 * </p>
 *
 */
@Service
public class FoodTypeServiceImpl extends ServiceImpl<FoodTypeMapper, FoodType> implements IFoodTypeService {

    @Autowired
    private FoodTypeMapper foodTypeMapper;
    /**
     * 获取分页数据
     * @param page
     * @return
     */
    public List<FoodType> list(Page page) {
        return foodTypeMapper.select(page);
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
            FoodType foodType = this.selectById(id);
            foodType.setDeleted(true);
            // 写入更新时间
            foodType.setUpdatedTime(new Date());
            // 如果需要删除的食品分类id不大于零 执行回滚操作
            if (!ObjectUtil.greaterThanZero(foodTypeMapper.updateById(foodType))) {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                return false;
            }
        }
        return true;
    }

}
