package cn.gtapc.ordering.service.impl;

import cn.gtapc.ordering.entity.Food;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.mapper.FoodMapper;
import cn.gtapc.ordering.mapper.FoodTypeMapper;
import cn.gtapc.ordering.service.IFoodService;
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
 * 食品 Service 实现
 * </p>
 *
 */
@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements IFoodService {


    @Autowired
    private FoodMapper foodMapper;
    /**
     * 获取分页数据
     * @param page
     * @return
     */
    public List<Food> list(Page page) {
        return foodMapper.select(page);
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
            Food food = this.selectById(id);
            food.setDeleted(true);
            // 写入更新时间
            food.setUpdatedTime(new Date());
            // 如果需要删除的食品id不大于零 执行回滚操作
            if (!ObjectUtil.greaterThanZero(foodMapper.updateById(food))) {
                TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
                return false;
            }
        }
        return true;
    }

}
