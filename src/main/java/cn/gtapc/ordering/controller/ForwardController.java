package cn.gtapc.ordering.controller;

import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.DinnerTableStatusEnum;
import cn.gtapc.ordering.entity.Food;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.entity.vo.OrderedVO;
import cn.gtapc.ordering.entity.vo.TemporaryOrderVO;
import cn.gtapc.ordering.service.IFoodService;
import cn.gtapc.ordering.service.IFoodTypeService;
import cn.gtapc.ordering.service.IOrderService;
import cn.gtapc.ordering.service.ITemporaryOrderService;
import cn.gtapc.util.common.ObjectUtil;
import cn.gtapc.util.common.ParseUtil;
import cn.gtapc.util.common.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前台页面转发
 */
@Controller
public class ForwardController extends BaseController {

    @Autowired
    private IFoodTypeService foodTypeService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private ITemporaryOrderService temporaryOrderService;
    @Autowired
    private IOrderService orderService;

    @GetMapping("/index.html")
    // 食品分类 id 分页
    public String index1(Model model, Long foodTypeId, String foodName, Integer current) {
        // 如果不是搜索食品默认显示招牌菜
        if (foodTypeId == null && StringUtil.isEmpty(foodName)) {
            foodTypeId = 1L;
        }
        // 获取食品分类数据
        List<FoodType> foodTypeList = foodTypeService.selectList(new EntityWrapper<FoodType>(new FoodType()));
        model.addAttribute("foodTypeList", foodTypeList);

        FoodType foodType = new FoodType();
        // 根据食品分类检索食品
        foodType.setId(foodTypeId);
        foodType = foodTypeService.selectById(foodType);
        model.addAttribute("foodType", foodType);

        Page<Food> page = this.getPage(0, 10, true);
        // 分页
        if (current != null) {
            page.setCurrent(current);
        }
        Food food = new Food();
        if (foodType != null) {
            food.setFoodTypeId(foodType.getId());
        }
        // 根据食品名称查询食品
        if ((page = foodService.selectPage(page, new EntityWrapper<Food>(food).like("name", foodName).orderBy("created_time", false).orderBy("updated_time", false))) != null) {
            model.addAttribute("foodList", page.getRecords());
        }
        model.addAttribute("page", page);
        // 将查询条件返回
        model.addAttribute("foodName", foodName);

        // 已点食品
        // TODO 根据当前用户获取餐桌 ID
        Long dinnerTableId = 1L;
        // 查询临时订单内的已点食品
        OrderedVO orderedVO = new OrderedVO();
        Order order = new Order();
        order.setDinnerTableId(dinnerTableId);
        order.setFinished(false);
        List<Order> orderList = orderService.selectList(new EntityWrapper<Order>(order));
        order = orderList.get(0);
        orderedVO = JSONObject.parseObject(JSONObject.toJSON(order).toString(), OrderedVO.class);

        TemporaryOrder temporaryOrder = new TemporaryOrder();
        temporaryOrder.setOrderId(order.getId());
        List<TemporaryOrder> temporaryOrderList = temporaryOrderService.selectList(new EntityWrapper<TemporaryOrder>(temporaryOrder).orderBy("finished"));
        List<TemporaryOrderVO> temporaryOrderVOList = new ArrayList<>();

        for (TemporaryOrder temporaryOrder1 : temporaryOrderList) {
            TemporaryOrderVO temporaryOrderVO = JSONObject.parseObject(JSONObject.toJSON(temporaryOrderService.selectById(temporaryOrder1.getId())).toString(), TemporaryOrderVO.class);
            temporaryOrderVO.setFoodName(foodService.selectById(temporaryOrderVO.getFoodId()).getName());

            temporaryOrderVOList.add(temporaryOrderVO);
        }
        orderedVO.setTemporaryOrderVOList(temporaryOrderVOList);

        model.addAttribute("orderedVO", orderedVO);

        return "index.ftl";
    }

    @GetMapping("/ordered.html")
    public String ordered(Model model) {
        OrderedVO orderedVO = new OrderedVO();

        // TODO 根据当前用户获取餐桌 ID
        Long dinnerTableId = 1L;
        // 正式订单信息
        Order order = new Order();
        order.setDinnerTableId(dinnerTableId);
        order.setFinished(false);
        List<Order> orderList = orderService.selectList(new EntityWrapper<Order>(order));
        if (orderList == null || ObjectUtil.lessThanAndEqualZero(orderList.size())) {
            order.setCreatedTime(new Date());
            if (orderService.insert(order)) {
            }
        }
        order = orderList.get(0);
        orderedVO = JSONObject.parseObject(JSONObject.toJSON(order).toString(), OrderedVO.class);
        // 临时订单信息
        TemporaryOrder temporaryOrder = new TemporaryOrder();
        temporaryOrder.setOrderId(order.getId());
        // 获取未完成的临时订单
        List<TemporaryOrder> temporaryOrderList = temporaryOrderService.selectList(new EntityWrapper<TemporaryOrder>(temporaryOrder).orderBy("finished"));
        List<TemporaryOrderVO> temporaryOrderVOList = new ArrayList<>();
        // 循环读取临时订单信息
        for (TemporaryOrder temporaryOrder1 : temporaryOrderList) {
            TemporaryOrderVO temporaryOrderVO = JSONObject.parseObject(JSONObject.toJSON(temporaryOrderService.selectById(temporaryOrder1.getId())).toString(), TemporaryOrderVO.class);
            if (temporaryOrderVO.getNumber() != 0 && temporaryOrderVO.getNumber() != null) {
                temporaryOrderVO.setFoodName(foodService.selectById(temporaryOrderVO.getFoodId()).getName());

                temporaryOrderVOList.add(temporaryOrderVO);
            }
        }
        orderedVO.setTemporaryOrderVOList(temporaryOrderVOList);

        model.addAttribute("orderedVO", orderedVO);
        // 临时订单总价
        Long totals = new Long(0);
        Long price = new Long(0);
        for (TemporaryOrder temporaryOrder1 : temporaryOrderList) {
            TemporaryOrderVO temporaryOrderVO = JSONObject.parseObject(JSONObject.toJSON(temporaryOrderService.selectById(temporaryOrder1.getId())).toString(), TemporaryOrderVO.class);
            price = temporaryOrderVO.getTotalPrice();
            totals = totals + price;
        }
        model.addAttribute("totals", totals);

        List<Food> foodList = foodService.selectList(new EntityWrapper<Food>(new Food()));
        model.addAttribute("foodList", foodList);

        return "ordered.ftl";
    }

    @GetMapping("/checkout.html")
    public String checkout(Model model) {
        OrderedVO orderedVO = new OrderedVO();

        // TODO 根据当前用户获取餐桌 ID
        Long dinnerTableId = 1L;
        // 正式订单信息
        Order order = new Order();
        order.setDinnerTableId(dinnerTableId);
        order.setFinished(false);
        List<Order> orderList = orderService.selectList(new EntityWrapper<Order>(order));
        order = orderList.get(0);
        orderedVO = JSONObject.parseObject(JSONObject.toJSON(order).toString(), OrderedVO.class);
        // 临时订单信息
        TemporaryOrder temporaryOrder = new TemporaryOrder();
        temporaryOrder.setOrderId(order.getId());
        temporaryOrder.setFinished(true);
        List<TemporaryOrder> temporaryOrderList = temporaryOrderService.selectList(new EntityWrapper<TemporaryOrder>(temporaryOrder));
        List<TemporaryOrderVO> temporaryOrderVOList = new ArrayList<>();
        for (TemporaryOrder temporaryOrder1 : temporaryOrderList) {
            TemporaryOrderVO temporaryOrderVO = JSONObject.parseObject(JSONObject.toJSON(temporaryOrderService.selectById(temporaryOrder1.getId())).toString(), TemporaryOrderVO.class);
            if (temporaryOrderVO.getNumber() != 0 && temporaryOrderVO.getNumber() != null) {
                temporaryOrderVO.setFoodName(foodService.selectById(temporaryOrderVO.getFoodId()).getName());

                temporaryOrderVOList.add(temporaryOrderVO);
            }
        }
        orderedVO.setTemporaryOrderVOList(temporaryOrderVOList);

        model.addAttribute("orderedVO", orderedVO);
        Long totals = new Long(0);
        Long price = new Long(0);
        for (TemporaryOrder temporaryOrder1 : temporaryOrderList) {
            TemporaryOrderVO temporaryOrderVO = JSONObject.parseObject(JSONObject.toJSON(temporaryOrderService.selectById(temporaryOrder1.getId())).toString(), TemporaryOrderVO.class);
            price = temporaryOrderVO.getTotalPrice();
            totals = totals + price;
        }
        model.addAttribute("totals", totals);

        List<Food> foodList = foodService.selectList(new EntityWrapper<Food>(new Food()));
        model.addAttribute("foodList", foodList);

        return "checkout.ftl";
    }

//    @GetMapping("/ordered_panel.html")
//    public String orderedPanel(Model model) {
//        List<TemporaryOrder> temporaryOrderList = temporaryOrderService.selectList(new EntityWrapper<TemporaryOrder>(new TemporaryOrder()));
//        model.addAttribute("temporaryOrderList", temporaryOrderList);
//
//        List<Food> foodList = foodService.selectList(new EntityWrapper<Food>(new Food()));
//        model.addAttribute("foodList", foodList);
//        return "ordered_panel.ftl";
//    }
//
//    @GetMapping("/ordered_panel1.html")
//    public String orderedPanel1(Model model) {
//
//
////        List<Food> foodList = foodService.selectList(new EntityWrapper<Food>(new Food()));
////        model.addAttribute("foodList", foodList);
//        return "ordered_panel1.ftl";
//    }

}
