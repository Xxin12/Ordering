package cn.gtapc.base.controller;

import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.util.common.ObjectUtil;
import cn.gtapc.util.common.ParseUtil;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 获取 MyBatis Plus Page 对象
     * 分页处理
     * @param limit       每页需要显示的数量
     * @param searchCount  是否返回查询到的数量
     * @param <T>
     * @return
     */
    protected <T> Page<T> getPage(int offset, int limit, boolean searchCount) {
        // 如果分页小于零
        if (ObjectUtil.lessThanZero(offset)) {
            offset = ParseUtil.parseInt(request.getParameter("offset"));
        }
        if (ObjectUtil.lessThanAndEqualZero(limit)) {
            limit = ParseUtil.parseInt(request.getParameter("limit"));
        }
        // 第一个参数为 current（当前页码） = offset（当前页码的第一行 - 1） / 10 + 1
        Page<T> page = new Page<>(offset / 10 + 1, limit);
        // 查出的数据
        page.setSearchCount(searchCount);
        return page;
    }

    protected <T> Page<T> getPage(int offset, int limit) {
        return getPage(offset, limit, false);
    }

    protected <T> Page<T> getPage(int limit) {
        return getPage(-1, limit, false);
    }

    // 默认为展示10条
    protected <T> Page<T> getManagePage() {
        return getPage(-1, 10, true);
    }

}
