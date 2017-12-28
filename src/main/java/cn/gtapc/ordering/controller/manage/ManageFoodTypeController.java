package cn.gtapc.ordering.controller.manage;


import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.controller.CommonController;
import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.service.IFoodTypeService;
import cn.gtapc.util.common.ObjectUtil;
import cn.gtapc.util.common.StringUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 食品分类 前端控制器
 * </p>
 */
@Controller
@RequestMapping("/manage/foodTypes")
public class ManageFoodTypeController extends BaseController {

    @Autowired
    private IFoodTypeService foodTypeService;

    @ResponseBody
    @GetMapping
    public Map<String, Object> list() {
        // 获取分页数据
        Page<FoodType> page = this.getManagePage();
        FoodType foodType = new FoodType();
        // 删除状态
        foodType.setDeleted(false);
        // EntityWrapper 是条件构造器
        List<FoodType> foodTypeList = foodTypeService.selectPage(page, new EntityWrapper<FoodType>(foodType)
                // 默认按 创建时间-更新时间-状态升序显示
                .orderBy("created_time", false)
                .orderBy("updated_time", false)).getRecords();
        // 按顺序输出食品分类数据及分页数据
        return RequestResultEnum.SUCCESS.getPageResult(page, foodTypeList);
    }

    /**
     * 新增食品分类
     * @return
     */
    @ResponseBody
    @PostMapping
    public Map<String, Object> create(FoodType foodType) {
        // 判断食品分类为空 或食品分类名字为空 返回错误
        if (foodType == null || (foodType != null && StringUtil.isEmpty(foodType.getName()))) {
            return RequestResultEnum.PARAM_ERROR.getResult();
        }
        // 写入创建时间
        foodType.setCreatedTime(new Date());
        // 如果插入返回成功，没有则返回失败
        if (foodTypeService.insert(foodType)) {
            return RequestResultEnum.SUCCESS.getResult();
        }
        return RequestResultEnum.FAILURE.getResult();
    }

    /**
     * 更改食品分类名字
     */
    @ResponseBody
    @PutMapping("/{id}")
    public Map<String, Object> update(@PathVariable Long id, FoodType foodType) {
        // 如果id小于等于零 或 分类为空 或 分类不为空但名字为空 返回错误
        if (ObjectUtil.lessThanAndEqualZero(id) || foodType==null || (foodType!=null && StringUtil.isEmpty(foodType.getName()))) {
            return RequestResultEnum.PARAM_ERROR.getResult();
        }

        FoodType updatingFoodType = new FoodType();
        updatingFoodType.setId(id);
        // 如果id为空返回不存在
        if ((updatingFoodType = foodTypeService.selectById(updatingFoodType)) == null) {
            return RequestResultEnum.FOOD_TYPE_NOT_EXIST.getResult();
        }
        // 写入名字
        updatingFoodType.setName(foodType.getName());
        // 写入更新时间
        updatingFoodType.setUpdatedTime(new Date());
        // 如果更新返回成功 ，没有则返回失败
        if (foodTypeService.updateById(updatingFoodType)) {
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
            // 需要删除的数据是否存在
            // ids.length 取得ids数组长度
            // 循环删除
            for (int i = 0; i < ids.length; i++) {
                FoodType foodType = new FoodType();
                foodType.setId(ids[i]);
                foodType.setDeleted(false);
                // 判断食品分类是否为空
                if (foodTypeService.selectById(foodType) == null) {
                    // 返回食品分类不存在
                    return RequestResultEnum.DINNER_TABLE_NOT_EXIST.getResult();
                }
            }
            // 删除 返回成功
            if (foodTypeService.delete(ids)) {
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
