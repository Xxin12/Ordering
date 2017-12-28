package cn.gtapc.ordering.mapper;

import cn.gtapc.ordering.entity.Food;
import cn.gtapc.ordering.entity.FoodType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
/**
 * <p>
 * 食品 Mapper 接口
 * </p>
 */
public interface FoodMapper extends BaseMapper<Food> {

    List<Food> select(Page<Food> page);

}