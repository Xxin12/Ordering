package cn.gtapc.ordering.controller;

import cn.gtapc.base.controller.BaseController;
import cn.gtapc.ordering.constant.RequestResultEnum;
import cn.gtapc.util.common.ObjectUtil;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CommonController extends BaseController {

    // 将要被操作的 ID 集合以及对应 RESTFul 的 Key
    public static Map<String, long[]> toBeOperationIds = new HashMap();

    @ResponseBody
    @PostMapping("/batchOperation")
    public Map<String, Object> batchOperation(@RequestParam(value = "ids[]") long[] ids) {
        if (ObjectUtil.greaterThanZero(ids)) {
            String key = new SimpleHash("MD5", ids.toString(), null, 100).toString();
            this.toBeOperationIds.put(key, ids);
            return RequestResultEnum.SUCCESS.getResult(key);
        }
        return RequestResultEnum.FAILURE.getResult();
    }

}