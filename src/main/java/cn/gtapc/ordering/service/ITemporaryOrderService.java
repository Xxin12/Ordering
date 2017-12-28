package cn.gtapc.ordering.service;

import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.entity.vo.TemporaryOrderVO;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ITemporaryOrderService extends IService<TemporaryOrder> {

    List<TemporaryOrderVO> list(Page page,Boolean temporaryOrderFinished);

    List<TemporaryOrder> list(long dinnerTableId);

    boolean update(long[] temporaryOrder2)throws Exception;

    boolean delete(long[] deleted);
}
