package cn.gtapc.ordering.controller;

import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.DinnerTableStatusEnum;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.service.IDinnerTableService;
import cn.gtapc.ordering.service.IFoodTypeService;
import cn.gtapc.util.common.ObjectUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VOController extends BaseController {

    @Autowired
    IDinnerTableService dinnerTableService;
    @Autowired
    IFoodTypeService foodTypeService;

    @GetMapping("/index")
    @ResponseBody
    private Map<String, Object> index(Long dinnerTableId) {
        // 如果 餐桌id小于等于零 返回错误
        if (ObjectUtil.lessThanAndEqualZero(dinnerTableId)) {
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        DinnerTable dinnerTable = new DinnerTable();
        dinnerTable.setId(dinnerTableId);
        dinnerTable.setDeleted(false);
        // 如果餐桌id 为空返回餐桌不存在
        if ((dinnerTable = dinnerTableService.selectById(dinnerTable)) == null) {
            return RequestResultEnum.DINNER_TABLE_NOT_EXIST.getResult();
        }
        //
        if (dinnerTable.getStatus() != DinnerTableStatusEnum.EATING.getKey()) {
            return RequestResultEnum.DINNER_TABLE_STATUS_IS_WRONG.getResult();
        }

        Map<String, Object> dataMap = new HashMap<>();
        // 获取食品分类
        List<FoodType> foodTypeList = foodTypeService.selectList(new EntityWrapper<FoodType>());
        dataMap.put("footTypeList", foodTypeList);
        // TODO 根据食品分类（此处为招牌菜）获取食品列表

        return null;
    }
}
