package cn.gtapc.ordering.mapper;

import cn.gtapc.ordering.entity.DinnerTable;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;
/**
 * <p>
 * 餐桌 Mapper 接口
 * </p>
 */
public interface DinnerTableMapper extends BaseMapper<DinnerTable> {

    List<DinnerTable> select(Page<DinnerTable> page);

}