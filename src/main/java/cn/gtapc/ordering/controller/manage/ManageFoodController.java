package cn.gtapc.ordering.controller.manage;


import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.controller.CommonController;
import cn.gtapc.ordering.entity.Food;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.service.IFoodService;
import cn.gtapc.ordering.service.IFoodTypeService;
import cn.gtapc.util.common.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 食品前端控制器
 */
@Controller
@RequestMapping("/manage/foods")
public class ManageFoodController extends BaseController {

    @Autowired
    private IFoodService foodService;

    @ResponseBody
    @GetMapping
    public Map<String, Object> list(Long foodTypeId) {
        // 获取分页数据
        Page<Food> page = this.getManagePage();
        Food food = new Food();
        // 根据食品分类检索食品
        food.setFoodTypeId(foodTypeId);
        // 删除状态
        food.setDeleted(false);
        // EntityWrapper 是条件构造器
        List<Food> foodList = foodService.selectPage(page, new EntityWrapper<Food>(food)
                // 默认按 创建时间-更新时间-状态升序显示
                .orderBy("created_time", false)
                .orderBy("updated_time", false)).getRecords();
        // 按顺序输出食品数据及分页数据
        return RequestResultEnum.SUCCESS.getPageResult(page, foodList);
    }

    /**
     * 新增食品
     * @return
     */
    @ResponseBody
    @PostMapping
    public Map<String, Object> create(Food food) {
        if (food == null) {
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        if (food != null && StringUtil.isEmpty(food.getName())){
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        if (food!=null && ObjectUtil.lessThanAndEqualZero(food.getPrice())){
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        if (food.getImageUrl()== null){
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        if (food.getFoodTypeId() == null){
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        food.setPrice(food.getPrice() * 100);
        food.setCreatedTime(new Date());
        // 如果插入返回成功，没有则返回失败
        if (foodService.insert(food)) {
            return RequestResultEnum.SUCCESS.getResult();
        }
        return RequestResultEnum.FAILURE.getResult();
    }


    /**
     * 更改食品分类名字
     */
    @ResponseBody
    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, Food food) {
        // 如果id小于等于零 或 分类为空 或 分类不为空但名字为空 返回错误
        if (ObjectUtil.lessThanAndEqualZero(id) || food==null || (food!=null && StringUtil.isEmpty(food.getName()))
                ||(food!=null && ObjectUtil.lessThanAndEqualZero(food.getPrice()))
                ||(food!=null && ObjectUtil.lessThanAndEqualZero(food.getFoodTypeId()))
                ||(food!=null && food.getImageUrl()==null)
                ) {
            return RequestResultEnum.PARAM_ERROR.getResult();
        }

        Food updatingFood = new Food();
        updatingFood.setId(id);
        // 如果id为空返回不存在
        if ((updatingFood = foodService.selectById(updatingFood)) == null) {
            return RequestResultEnum.FOOD_NOT_EXIST.getResult();
        }
        // 写入名字
        updatingFood.setName(food.getName());
        updatingFood.setFoodTypeId(food.getFoodTypeId());
        updatingFood.setPrice(food.getPrice() * 100);
        updatingFood.setImageUrl(food.getImageUrl());
        // 写入更新时间
        updatingFood.setUpdatedTime(new Date());
        // 如果更新返回成功 ，没有则返回失败
        if (foodService.updateById(updatingFood)) {
            return RequestResultEnum.SUCCESS.getResult();
        }

        return RequestResultEnum.FAILURE.getResult();
    }

    /**
     * 删除多条数据
     * @param key
     * @return
     */
    @ResponseBody
    @DeleteMapping("/{key}")
    public Map<String, Object> delete(@PathVariable String key) {
        long[] ids = null;
        // 需要删除的数据是否存在
        // 如果 key为空或ids[] 不大于零
        if (StringUtil.isEmpty(key) || !ObjectUtil.greaterThanZero((ids = CommonController.toBeOperationIds.get(key)))) {
            // 返回错误
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        try {
            // ids.length 取得ids数组长度
            // 循环删除
            for (int i = 0; i < ids.length; i++) {
                Food food = new Food();
                food.setId(ids[i]);
                food.setDeleted(false);
                // 判断食品是否为空
                if (foodService.selectById(food) == null) {
                    // 返回食品不存在
                    return RequestResultEnum.FOOD_NOT_EXIST.getResult();
                }
            }
            // 删除 返回成功
            if (foodService.delete(ids)) {
                return RequestResultEnum.SUCCESS.getResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return RequestResultEnum.FAILURE.getResult();
        } finally {
            // 异常后清空数据
            CommonController.toBeOperationIds.remove(key);
        }
        return RequestResultEnum.FAILURE.getResult();
    }
}
