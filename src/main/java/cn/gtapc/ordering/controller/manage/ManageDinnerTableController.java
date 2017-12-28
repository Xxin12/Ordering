package cn.gtapc.ordering.controller.manage;

import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.DinnerTableStatusEnum;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.controller.CommonController;
import cn.gtapc.ordering.entity.DinnerTable;
import cn.gtapc.ordering.service.IDinnerTableService;
import cn.gtapc.util.common.ObjectUtil;
import cn.gtapc.util.common.StringUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 餐桌前端控制器
 */
@Controller
@RequestMapping("/manage/dinnerTables")
public class ManageDinnerTableController extends BaseController {

    @Autowired
    private IDinnerTableService dinnerTableService;

    /**
     *
     * @return
     */
    @ResponseBody
    @GetMapping
    public Map<String, Object> list() {
        // 获取分页数据
        Page<DinnerTable> page = this.getManagePage();
        DinnerTable dinnerTable = new DinnerTable();
        // 删除状态
        dinnerTable.setDeleted(false);
        // EntityWrapper 是条件构造器
        List<DinnerTable> dinnerTableList = dinnerTableService.selectPage(page, new EntityWrapper<DinnerTable>(dinnerTable)
                // 默认按 创建时间-更新时间-状态降序显示
                .orderBy("created_time", false)
                .orderBy("updated_time", false)
                .orderBy("status", false)).getRecords();
        // 按顺序输出餐桌数据及分页数据
        return RequestResultEnum.SUCCESS.getPageResult(page, dinnerTableList);
    }

    /**
     * 新增餐桌
     * @return
     */
    @ResponseBody
    @PostMapping
    public Map<String, Object> create() {
        DinnerTable dinnerTable = new DinnerTable();
        // 写入餐桌状态
        dinnerTable.setStatus(DinnerTableStatusEnum.EMPTY.getKey());
        // 写入创建时间
        dinnerTable.setCreatedTime(new Date());
        // 如果新增一条数据
        if (dinnerTableService.insert(dinnerTable)) {
            //返回成功
            return RequestResultEnum.SUCCESS.getResult();
        }
        // 返回失败
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
                DinnerTable dinnerTable = new DinnerTable();
                dinnerTable.setId(ids[i]);
                dinnerTable.setDeleted(false);
                // 判断餐桌是否为空
                if (dinnerTableService.selectById(dinnerTable) == null) {
                    // 返回餐桌不存在
                    return RequestResultEnum.DINNER_TABLE_NOT_EXIST.getResult();
                }
            }
            // 删除 返回成功
            if (dinnerTableService.delete(ids)) {
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

    /**
     * 修改餐桌数据，此处仅修改餐桌状态
     *
     * @param key 需要修改的餐桌 ID
     * @return
     */
    @ResponseBody
    @PutMapping("/{key}")
    public Map<String, Object> update(@PathVariable String key) {
        long[] ids = null;
        // 需要删除的数据是否存在
        // 如果 key为空或ids[] 不大于零
        if (StringUtil.isEmpty(key) || !ObjectUtil.greaterThanZero((ids = CommonController.toBeOperationIds.get(key)))) {
            // 返回错误
            return RequestResultEnum.PARAM_ERROR.getResult();
        }

        try {
            // ids.length 取得ids数组长度
            // 循环修改
            for (int i = 0; i < ids.length; i++) {
                DinnerTable dinnerTable = new DinnerTable();
                dinnerTable.setId(ids[i]);
                dinnerTable.setDeleted(false);
                // 判断餐桌是否为空
                if ((dinnerTable = dinnerTableService.selectById(dinnerTable)) == null) {
                    // 返回餐桌不存在
                    return RequestResultEnum.DINNER_TABLE_NOT_EXIST.getResult();
                }
            }
            // 修改更新数据
            if (dinnerTableService.update(ids)) {
                // 返回成功
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
