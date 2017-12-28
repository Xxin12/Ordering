package cn.gtapc.ordering.mapper;

import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.entity.FoodType;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
/**
 * <p>
 * 分类 Mapper 接口
 * </p>
 */
public interface FoodTypeMapper extends BaseMapper<FoodType> {

    List<FoodType> select(Page<FoodType> page);

}