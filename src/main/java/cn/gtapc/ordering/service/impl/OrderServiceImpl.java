package cn.gtapc.ordering.service.impl;

import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.mapper.OrderMapper;
import cn.gtapc.ordering.service.IOrderService;
import cn.gtapc.util.common.ObjectUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 订单 Service 实现
 * </p>
 *
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> list(Page page) {
        return orderMapper.select(page);
    }
}
