package cn.gtapc.ordering.controller.manage;


import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.entity.Order;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.service.IFoodService;
import cn.gtapc.ordering.service.IOrderService;
import cn.gtapc.ordering.service.ITemporaryOrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/orders")
public class ManageOrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    @ResponseBody
    @GetMapping
    public Map<String, Object> list() {
        // 获取分页数据
        Page<Order> page = this.getManagePage();
        Order order = new Order();
        // 删除状态
        order.setDeleted(false);
        // EntityWrapper 是条件构造器
        List<Order> orderList = orderService.selectPage(page, new EntityWrapper<Order>(order)
                // 默认按 创建时间-更新时间-状态升序显示
                .orderBy("created_time", false)
                .orderBy("updated_time", false)).getRecords();
        // 按顺序输出临时订单数据及分页数据
        return RequestResultEnum.SUCCESS.getPageResult(page, orderList);
    }


}
