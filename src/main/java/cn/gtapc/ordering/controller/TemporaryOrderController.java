package cn.gtapc.ordering.controller;


import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.entity.Food;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.entity.vo.OrderedVO;
import cn.gtapc.ordering.entity.vo.TemporaryOrderVO;
import cn.gtapc.ordering.service.IFoodService;
import cn.gtapc.ordering.service.IOrderService;
import cn.gtapc.ordering.service.ITemporaryOrderService;
import cn.gtapc.util.common.ObjectUtil;
import cn.gtapc.util.common.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/temporaryOrders")
public class TemporaryOrderController {

    @Autowired
    private ITemporaryOrderService temporaryOrderService;

    @Autowired
    private IOrderService orderService;
    @Autowired
    private IFoodService foodService;

    @ResponseBody
    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, Integer number, Long foodId, TemporaryOrder temporaryOrders) {

        if (ObjectUtil.lessThanZero(number)) {
            return RequestResultEnum.PARAM_ERROR.getResult();
        }


        if (id == null || id == 0) {
            // TODO 1. 查询当前登录用户餐桌 ID 对应的订单
            Long dinnerTableId = 1l;


            // TODO 2. 创建临时订单
            Food food = new Food();

            food.setId(temporaryOrders.getFoodId());
            if ((food = foodService.selectById(food)) == null) {
                return RequestResultEnum.FOOD_NOT_EXIST.getResult();
            }

            //创建时间
            temporaryOrders.setCreatedTime(new Date());
            //数量
            temporaryOrders.setNumber(number);
            //食品id
            temporaryOrders.setFoodId(foodId);

            temporaryOrders.setTotalPrice(food.getPrice() * number);

            Order order = new Order();
            order.setDinnerTableId(dinnerTableId);
            order.setFinished(false);
            List<Order> orderList = orderService.selectList(new EntityWrapper<Order>(order).orderBy("finished"));
            order = orderList.get(0);

            temporaryOrders.setOrderId(order.getId());

            if (temporaryOrderService.insert(temporaryOrders)) {
                return RequestResultEnum.SUCCESS.getResult();
            }
            //返回成功
            return RequestResultEnum.FAILURE.getResult();
        }else {
            Order order = null;
            TemporaryOrder temporaryOrder = new TemporaryOrder();
            temporaryOrder.setId(id);
//        temporaryOrder.setDeleted(null);
            if ((temporaryOrder = temporaryOrderService.selectById(temporaryOrder)) == null) {
                return RequestResultEnum.TEMPORARY_ORDER_NOT_EXIST.getResult();
            }
            if (temporaryOrder.getFinished()) {
                return RequestResultEnum.TEMPORARY_ORDER_FINISHED.getResult();
            }

            Food food = new Food();
            food.setId(temporaryOrder.getFoodId());
            if ((food = foodService.selectById(food)) == null) {
                return RequestResultEnum.FOOD_NOT_EXIST.getResult();
            }

            temporaryOrder.setTotalPrice(food.getPrice() * number);
            temporaryOrder.setNumber(number);
            temporaryOrder.setUpdatedTime(new Date());
//        if (number == 0) {
//            temporaryOrder.setDeleted(true);
//        }else{
//            temporaryOrder.setDeleted(false);
//        }
            if (temporaryOrderService.updateById(temporaryOrder)) {
                return RequestResultEnum.SUCCESS.getResult();
            }
        }

        // TODO 3. 更新临时订单（总价格、更新时间等）

        return RequestResultEnum.FAILURE.getResult();
    }

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
        List<Order> orderList = orderService.selectList(new EntityWrapper<Order>(order).orderBy("finished"));
        order = orderList.get(0);
        orderedVO = JSONObject.parseObject(JSONObject.toJSON(order).toString(), OrderedVO.class);
        // 临时订单信息
        TemporaryOrder temporaryOrder = new TemporaryOrder();
        temporaryOrder.setOrderId(order.getId());

        // 获取未完成的临时订单
        List<TemporaryOrder> temporaryOrderList = temporaryOrderService.selectList(new EntityWrapper<TemporaryOrder>(temporaryOrder).orderBy("finished"));
        List<TemporaryOrderVO> temporaryOrderVOList = new ArrayList<>();

        long totalPrice = 0;

        long temporaryOrder2[] =new long[temporaryOrderList.size()];
        int i = 0;

        for (TemporaryOrder temporaryOrder1 : temporaryOrderList) {
            temporaryOrder2[ i ] = temporaryOrder1.getId();
            temporaryOrderService.selectById(temporaryOrder1.getId());
            totalPrice = totalPrice + temporaryOrder1.getTotalPrice();
            i++;
        }

        try {
            if (temporaryOrderService.update(temporaryOrder2)){
                order.setTotalPrice(order.getTotalPrice() + totalPrice);
                order.setUpdatedTime(new Date());
                if (orderService.updateById(order)){
                    return RequestResultEnum.SUCCESS.getResult();
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
