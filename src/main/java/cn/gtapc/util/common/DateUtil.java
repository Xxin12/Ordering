package cn.gtapc.util.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 時間工具類
 *
 * @author duanluan
 */
public final class DateUtil {

    /**
     * 获取当前时间的毫秒数
     *
     * @return
     */
    public static Long getUnixTime() {
        return new Date().getTime() / 1000;
    }

    /**
     * 將時間字符串解析為 Date 類型
     *
     * @param date         需要解析的時間字符串
     * @param parsePattern 解析格式，如果為空默認為“yyyy-MM-dd HH:mm:ss”
     * @return
     */
    public static Date parseDate(String date, String parsePattern) {
        try {
            if (StringUtil.isNotEmpty(parsePattern)) {
                return DateUtils.parseDate(date, parsePattern);
            } else {
                return DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss");
            }
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 將時間字符串解析為 Calendar 類型
     *
     * @param date         需要解析的時間字符串
     * @param parsePattern 解析格式，如果為空默認為“yyyy-MM-dd HH:mm:ss”
     * @return
     */
    public static Calendar parseCalendar(String date, String parsePattern) {
        try {
            if (StringUtil.isNotEmpty(parsePattern)) {
                return DateUtils.toCalendar(DateUtils.parseDate(date, parsePattern));
            } else {
                return DateUtils.toCalendar(DateUtils.parseDate(date, "yyyy-MM-dd HH:mm:ss"));
            }
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 格式化時間
     *
     * @param date    時間
     * @param pattern 格式化後的時間格式，如果為空默認為“yyyy-MM-dd HH:mm:ss”
     * @return
     */
    public static String format(Date date,
                                String pattern) {
        if (StringUtil.isNotEmpty(pattern)) {
            return DateFormatUtils.format(date, pattern);
        } else {
            return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 格式化時間
     *
     * @param date    時間
     * @param pattern 格式化後的時間格式，如果為空默認為“yyyy-MM-dd HH:mm:ss”
     * @return
     */
    public static String format(Calendar date,
                                String pattern) {
        if (StringUtil.isNotEmpty(pattern)) {
            return DateFormatUtils.format(date, pattern);
        } else {
            return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        }
    }

    /**
     * 格式化時間
     *
     * @param date            時間
     * @param originalPattern 格式化前的時間格式，如果為空默認為“yyyy-MM-dd HH:mm:ss”
     * @param pattern         格式化後的時間格式，如果為空默認為“yyyy-MM-dd HH:mm:ss”
     * @return
     */
    public static String format(String date, String originalPattern,
                                String pattern) {
        if (StringUtil.isNotEmpty(pattern)) {
            return DateFormatUtils.format(parseDate(date, originalPattern), pattern);
        } else {
            return DateFormatUtils.format(parseDate(date, originalPattern), "yyyy-MM-dd HH:mm:ss");
        }
    }


    /**
     * 獲取天的開始時間
     *
     * @param date    需要获取的時間
     * @param pattern 需要获取的時間的格式，為空則默認 yyyy-MM-dd HH:mm:ss
     * @return 天的開始時間
     */
    public static Date getFirstTimeOfDay(String date, String pattern) {
        if (StringUtil.isNotEmpty(date)) {
            Calendar calendar = parseCalendar(date, pattern);
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMinimum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取天的開始時間
     *
     * @param date 需要获取的時間
     * @return 返回天的開始時間
     */
    public static Date getFirstTimeOfDay(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMinimum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取天的結束時間
     *
     * @param date    需要获取的時間
     * @param pattern 需要获取的時間的格式，為空則默認 yyyy-MM-dd HH:mm:ss
     * @return 返回天的結束時間
     */
    public static Date getLastTimeOfDay(String date, String pattern) {
        if (StringUtils.isNotEmpty(date)) {
            Calendar calendar = parseCalendar(date, pattern);
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMaximum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取天的結束時間
     *
     * @param date 需要获取的時間
     * @return 返回天的開始時間
     */
    public static Date getLastTimeOfDay(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMaximum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取月的第一天的開始時間
     *
     * @param date    需要获取的時間
     * @param pattern 需要获取的時間的格式，為空則默認 yyyy-MM-dd HH:mm:ss
     * @return 返回月的第一天
     */
    public static Date getFirstTimeOfMonth(String date, String pattern) {
        if (StringUtils.isNotEmpty(date)) {
            Calendar calendar = parseCalendar(date, pattern);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMinimum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMinimum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取月的第一天的開始時間
     *
     * @param date 需要获取的時間
     * @return 返回月的第一天
     */
    public static Date getFirstTimeOfMonth(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMinimum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMinimum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取月的最後一天的結束時間
     *
     * @param date    需要获取的時間
     * @param pattern 需要获取的時間的格式，為空則默認 yyyy-MM-dd HH:mm:ss
     * @return 返回月的最後一天
     */
    public static Date getLastTimeOfMonth(String date, String pattern) {
        if (StringUtils.isNotEmpty(date)) {
            Calendar calendar = parseCalendar(date, pattern);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMaximum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取月的最後一天的結束時間
     *
     * @param date 需要获取的時間
     * @return 返回月的最後一天
     */
    public static Date getLastTimeOfMonth(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMaximum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取年的第一天的開始時間
     *
     * @param date    需要获取的時間
     * @param pattern 需要获取的時間的格式，為空則默認 yyyy-MM-dd HH:mm:ss
     * @return 返回年的第一天的開始時間
     */
    public static Date getFirstTimeOfYear(String date, String pattern) {
        if (StringUtils.isNotEmpty(date)) {
            Calendar calendar = parseCalendar(date, pattern);
            calendar.set(Calendar.MONTH, calendar.getMinimum(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMinimum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMinimum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取年的第一天的開始時間
     *
     * @param date 需要获取的時間
     * @return 返回年的第一天的開始時間
     */
    public static Date getFirstTimeOfYear(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.getMinimum(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMinimum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMinimum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMinimum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取年的最後一天的最後時間
     *
     * @param date 需要获取的時間，為空則默認 yyyy-MM-dd HH:mm:ss
     * @return 返回年的最後一天的最後時間
     */
    public static Date getLastTimeOfYear(String date, String pattern) {
        if (StringUtils.isNotEmpty(date)) {
            Calendar calendar = parseCalendar(date, pattern);
            calendar.set(Calendar.MONTH, calendar.getMaximum(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMaximum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    /**
     * 獲取年的最後一天的最後時間
     *
     * @param date 需要获取的時間
     * @return 返回年的最後一天的最後時間
     */
    public static Date getLastTimeOfYear(Date date) {
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.MONTH, calendar.getMaximum(Calendar.MONTH));
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getMaximum(Calendar.DAY_OF_MONTH));
            calendar.set(Calendar.HOUR_OF_DAY,
                    calendar.getMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getMaximum(Calendar.SECOND));
            calendar.set(Calendar.MILLISECOND,
                    calendar.getMaximum(Calendar.MILLISECOND));
            return calendar.getTime();
        }
        return null;
    }

    // /**
    //  * 获取节假日
    //  * 工作日對應結果為 0, 休息日對應結果為 1, 節假日對應的結果為 2
    //  * 支持 2010 - 今年的中國法定節假日
    //  *
    //  * @param year 年份
    //  * @return
    //  */
    // public static Map<String, String> getHoliday(int year) {
    //     Map<String, String> holidayMap = new HashMap<String, String>();
    //
    //     StringBuilder paramStr = new StringBuilder("http://www.easybots.cn/api/holiday.php?m=");
    //     List<String> paramList = new ArrayList<String>();
    //
    //     // 拼接 URL 参数
    //     for (int i = 1; i <= 12; i++) {
    //         if (i < 10) {
    //             paramStr.append(year).append("0").append(i);
    //             paramList.add(year + "0" + i);
    //         } else {
    //             paramStr.append(year).append(i);
    //             paramList.add("" + year + i);
    //         }
    //
    //         if (i != 12) {
    //             paramStr.append(",");
    //         }
    //     }
    //
    //     // 获取节假日 JSON
    //     String holidayJson = HttpUtil.get(paramStr.toString(), null);
    //
    //     // 解析 JSON
    //     ObjectMapper mapper = new ObjectMapper();
    //     try {
    //         // 获取所有月份 JSON Node
    //         JsonNode monthNode = mapper.readValue(holidayJson, JsonNode.class);
    //
    //         for (int i = 1; i <= 12; i++) {
    //             JsonNode dayNode = monthNode.get(paramList.get(i - 1));
    //             Iterator<String> dayIterator = dayNode.fieldNames();
    //             while (dayIterator.hasNext()) {
    //                 String month = "";
    //                 if (i < 10) {
    //                     month = "0" + i;
    //                 } else {
    //                     month = "" + i;
    //                 }
    //
    //                 String day = dayIterator.next();
    //                 holidayMap.put(year + "-" + month + "-" + day, dayNode.get(day).asText());
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    //
    //     return holidayMap;
    // }

}