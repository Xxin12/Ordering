package cn.gtapc.ordering.service;

import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.ordering.entity.DinnerTable;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
/**
 * <p>
 * 餐桌 Service 接口
 * </p>
 */
public interface IDinnerTableService extends IService<DinnerTable> {

    // 分页数据
    List<DinnerTable> list(Page page);

    /**
     * 删除多条数据
     *
     * @param ids 需要删除的数据 ID 数组
     * @return
     */
    boolean delete(long[] ids) throws Exception;

    /**
     * 修改多条数据
     *
     * @param ids 需要修改的数据 ID 数组
     * @return
     * @throws Exception
     */
    boolean update(long[] ids) throws Exception;

}
