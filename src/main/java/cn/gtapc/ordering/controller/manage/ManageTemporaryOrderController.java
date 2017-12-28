package cn.gtapc.ordering.controller.manage;


import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.controller.CommonController;
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
import cn.gtapc.util.common.StringUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/temporaryOrders")
public class ManageTemporaryOrderController extends BaseController {

    @Autowired
    private ITemporaryOrderService temporaryOrderService;

    @ResponseBody
    @GetMapping
    public Map<String, Object> list(Boolean finished) {
        if (StringUtil.isEmpty(finished)) {
            return RequestResultEnum.PARAM_ERROR.getResult();
        }

        Page<TemporaryOrderVO> page = this.getManagePage();
        return RequestResultEnum.SUCCESS.getPageResult(page, temporaryOrderService.list(page, finished));
    }

}
