package cn.gtapc.ordering.service;

import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.entity.TemporaryOrder;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 订单 Service 接口
 * </p>
 */
public interface IOrderService extends IService<Order> {
    List<Order> list(Page page);

}
