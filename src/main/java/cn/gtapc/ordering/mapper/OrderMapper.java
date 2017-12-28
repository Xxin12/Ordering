package cn.gtapc.ordering.mapper;

import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.entity.TemporaryOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 */
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> select(Page<TemporaryOrder> page);

}