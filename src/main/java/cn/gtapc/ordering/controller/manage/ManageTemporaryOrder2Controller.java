package cn.gtapc.ordering.controller.manage;


import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.controller.CommonController;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.service.IFoodService;
import cn.gtapc.ordering.service.ITemporaryOrderService;
import cn.gtapc.util.common.ObjectUtil;
import cn.gtapc.util.common.StringUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/manage/temporaryOrders2")
public class ManageTemporaryOrder2Controller extends BaseController{

    @Autowired
    private ITemporaryOrderService temporaryOrderService;
    private IFoodService foodService;

    @ResponseBody
    @GetMapping
    public Map<String, Object> list() {
        // 获取分页数据
        Page<TemporaryOrder> page = this.getManagePage();
        TemporaryOrder temporaryOrder = new TemporaryOrder();
        // 删除状态
        temporaryOrder.setDeleted(false);
        temporaryOrder.setFinished(true);
        // EntityWrapper 是条件构造器
        List<TemporaryOrder> temporaryOrderList = temporaryOrderService.selectPage(page, new EntityWrapper<TemporaryOrder>(temporaryOrder)
                // 默认按 创建时间-更新时间-状态升序显示
                .orderBy("created_time", false)
                .orderBy("updated_time", false)).getRecords();
        // 按顺序输出临时订单数据及分页数据
        return RequestResultEnum.SUCCESS.getPageResult(page, temporaryOrderList);
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
                TemporaryOrder temporaryOrder = new TemporaryOrder();
                temporaryOrder.setId(ids[i]);
                temporaryOrder.setDeleted(false);
                // 判断食品分类是否为空
                if (temporaryOrderService.selectById(temporaryOrder) == null) {
                    // 返回食品分类不存在
                    return RequestResultEnum.TEMPORARY_ORDER_NOT_EXIST.getResult();
                }
            }
            // 删除 返回成功
            if (temporaryOrderService.delete(ids)) {
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
