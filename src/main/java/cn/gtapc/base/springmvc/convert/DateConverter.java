package cn.gtapc.base.springmvc.convert;

import cn.gtapc.util.common.DateUtil;
import cn.gtapc.util.common.ParseUtil;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 全局日期處理類
 * Convert<T, S>
 * 泛型 T：代表客戶端提交的參數 String
 * 泛型 S：通過 convert 轉換的類型
 */
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String str) {
        String parsePattern = null;
        if (str.length() == 7) {
            parsePattern = "yyyy-MM";
        }
        if (str.length() == 10) {
            parsePattern = "yyyy-MM-dd";
        }
        // 时间戳
        if(str.length() == 13){
            Date date = new Date();
            date.setTime(ParseUtil.parseLong(str));
            return date;
        }
        return DateUtil.parseDate(str, parsePattern);
    }

}
