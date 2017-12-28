package cn.gtapc.ordering.service;

import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.entity.FoodType;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * <p>
 * 食品分类 Service 接口
 * </p>
 */
public interface IFoodTypeService extends IService<FoodType> {

    List<FoodType> list(Page page);

    /**
     * 删除多条数据
     *
     * @param ids 需要删除的数据 ID 数组
     * @return
     */
    @Transactional
    boolean delete(long[] ids) throws Exception;
}
