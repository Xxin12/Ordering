package cn.gtapc.ordering.service;

import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.entity.Food;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * <p>
 * 食品 Service 接口
 * </p>
 */
public interface IFoodService extends IService<Food> {

    List<Food> list(Page page);

    /**
     * 删除多条数据
     *
     * @param ids 需要删除的数据 ID 数组
     * @return
     */
    @Transactional
    boolean delete(long[] ids) throws Exception;
}
