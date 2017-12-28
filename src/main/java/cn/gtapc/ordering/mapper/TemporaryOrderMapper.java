package cn.gtapc.ordering.mapper;

import cn.gtapc.ordering.entity.FoodType;
import cn.gtapc.ordering.entity.TemporaryOrder;
import cn.gtapc.ordering.entity.vo.TemporaryOrderVO;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TemporaryOrderMapper extends BaseMapper<TemporaryOrder> {

    List<TemporaryOrderVO> selectVO(Page<TemporaryOrder> page, @Param("temporaryOrderFinished")Boolean temporaryOrderFinished);

    List<TemporaryOrder> selectByDinnerTableId(@Param("dinnerTableId") long dinnerTableId);

}