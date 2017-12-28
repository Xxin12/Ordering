package cn.gtapc.ordering.controller.manage;

import cn.gtapc.ordering.constant.DinnerTableStatusEnum;
import cn.gtapc.ordering.entity.Food;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.service.IFoodService;
import cn.gtapc.ordering.service.IFoodTypeService;
import cn.gtapc.ordering.service.ITemporaryOrderService;
import cn.gtapc.util.common.ObjectUtil;
import cn.gtapc.util.common.StringUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.enterprise.inject.New;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台页面转发
 */
@Controller
@RequestMapping("/manage")
public class ManageForwardController {

    @Autowired
    private IFoodTypeService foodTypeService;
    @Autowired
    private IFoodService foodService;
    @Autowired
    private ITemporaryOrderService temporaryOrderService;

    @GetMapping("/index.html")
    public String index(Model model) {
        return "manage/index.ftl";
    }

    /**
     * 餐桌管理
     *
     * @param model
     * @return
     */
    @GetMapping("/dinner_table.html")
    public String dinnerTable(Model model) {
        // 传递餐桌状态
        model.addAttribute("dinnerTableStatusList", DinnerTableStatusEnum.listKeyAndValues());
        System.out.println(DinnerTableStatusEnum.listKeyAndValues());
        return "manage/dinner_table.ftl";
    }

    /**
     * 食品分类
     *
     * @param model
     * @return
     */
    @GetMapping("/food_type.html")
    public String foodType(Model model) {
        return "manage/food_type.ftl";
    }

    /**
     * 食品分类编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/food_type_detail.html")
    public String foodTypeDetail(Model model, Long id) {
        // 判断修改食品分类不为负
        if (ObjectUtil.greaterThanAndEqualZero(id)) {
            FoodType updatingfoodType = new FoodType();
            updatingfoodType.setId(id);
            // 查询更新选择的id
            updatingfoodType = foodTypeService.selectById(updatingfoodType);
            // 如果id为空返回空
            if (updatingfoodType == null) {
                return null;
            }
            // 传递更新后的数据
            model.addAttribute("foodType", updatingfoodType);
        }
        return "manage/food_type_detail.ftl";
    }

    /**
     * 食品管理
     *
     * @param model
     * @return
     */
    @GetMapping("/food.html")
    public String food(Model model) {

        // 获取食品分类数据并传递
        List<FoodType> foodTypeList = foodTypeService.selectList(new EntityWrapper<FoodType>(new FoodType()));
        model.addAttribute("foodTypeList", foodTypeList);

        return "manage/food.ftl";
    }

    /**
     * 食品编辑
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/food_detail.html")
    public String foodDetail(Model model, Long id) {
        List<FoodType> foodTypeList = foodTypeService.selectList(new EntityWrapper<FoodType>(new FoodType()));
        model.addAttribute("foodTypeList", foodTypeList);

        if (ObjectUtil.greaterThanZero(id)) {
            Food food = foodService.selectById(id);
            if (food == null) {
                return null;
            }
            model.addAttribute("food", food);
            model.addAttribute("foodTypeId", food.getFoodTypeId());
        }

        return "manage/food_detail.ftl";
    }


    /**
     * 临时订单
     *
     * @param model
     * @return
     */
    @GetMapping("/temporary_order.html")
    public String temporaryorder(Model model) {

        return "manage/temporary_order.ftl";
    }
    /**
     * 临时订单
     *
     * @param model
     * @return
     */
    @GetMapping("/temporary_order2.html")
    public String temporaryorder2(Model model) {

        return "manage/temporary_order2.ftl";
    }

    /**
     * 餐桌订单
     *
     * @param model
     * @return
     */
    @GetMapping("/_order.html")
    public String order(Model model) {

        return "manage/_order.ftl";
    }
    /**
     * 餐桌订单
     *
     * @param model
     * @return
     */
    @GetMapping("/_order2.html")
    public String order2(Model model) {

        return "manage/_order2.ftl";
    }


}
