package cn.gtapc.ordering.controller;


import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.entity.vo.OrderedVO;
import cn.gtapc.ordering.entity.vo.TemporaryOrderVO;
import cn.gtapc.ordering.service.IOrderService;
import cn.gtapc.ordering.service.ITemporaryOrderService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private ITemporaryOrderService temporaryOrderService;

    @Autowired
    private IOrderService orderService;

    @ResponseBody
    @PostMapping
    public Map<String, Object> update() {
        OrderedVO orderedVO = new OrderedVO();

        // TODO 根据当前用户获取餐桌 ID
        Long dinnerTableId = 1L;
        // 正式订单信息
        Order order = new Order();
        order.setDinnerTableId(dinnerTableId);
        order.setFinished(false);
        List<Order> orderList = orderService.selectList(new EntityWrapper<Order>(order));
        order = orderList.get(0);
        // 临时订单信息
        TemporaryOrder temporaryOrder = new TemporaryOrder();
        temporaryOrder.setOrderId(order.getId());

        // 获取未完成的临时订单
        List<TemporaryOrder> temporaryOrderList = temporaryOrderService.selectList(new EntityWrapper<TemporaryOrder>(temporaryOrder).orderBy("finished"));
        List<TemporaryOrderVO> temporaryOrderVOList = new ArrayList<>();


        long deleted[] =new long[temporaryOrderList.size()];
        int i = 0;

        for (TemporaryOrder temporaryOrder1 : temporaryOrderList) {
            deleted[ i ] = temporaryOrder1.getId();
            temporaryOrderService.selectById(temporaryOrder1.getId());
            i++;
        }

        try {
            if (temporaryOrderService.delete(deleted)){

                order.setFinished(true);
                order.setFinishedTime(new Date());
                if (orderService.updateById(order)){
                    Order order1 = new Order();
                    order1.setDinnerTableId(dinnerTableId);
                    order1.setCreatedTime(new Date());
                    order1.setTotalPrice((long)0);
                    if (orderService.insert(order1)){
                        return RequestResultEnum.SUCCESS.getResult();
                    }
                }
                return RequestResultEnum.FAILURE.getResult();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return RequestResultEnum.FAILURE.getResult();
        } finally {
            // 异常后清空数据
            CommonController.toBeOperationIds.remove(temporaryOrderList);
        }

        // 如果插入返回成功，没有则返回失败
        return RequestResultEnum.FAILURE.getResult();

    }
}
