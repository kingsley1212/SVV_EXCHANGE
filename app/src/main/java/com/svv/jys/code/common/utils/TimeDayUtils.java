package com.svv.jys.code.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by  kingsley on 2019/12/3 0003.
 */
public class TimeDayUtils {
    /**
     * 根据当前日期获得是星期几
     * time=yyyy-MM-dd
     * @return
     */
    public static int getWeekDay(long seconds) {

        if ((seconds+"").length() < 13) {//确定毫秒级
            seconds  *= 1000;
        }

        Date date = new Date(seconds);
        String Week = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int wek = c.get(Calendar.DAY_OF_WEEK);
        if (wek == 1) {
            Week += "星期日";
        }
        if (wek == 2) {
            Week += "星期一";
        }
        if (wek == 3) {
            Week += "星期二";
        }
        if (wek == 4) {
            Week += "星期三";
        }
        if (wek == 5) {
            Week += "星期四";
        }
        if (wek == 6) {
            Week += "星期五";
        }
        if (wek == 7) {
            Week += "星期六";
        }
        return wek;
    }

    /**
     * <pre>
     * 判断date和当前日期是否在同一周内
     * 注:
     * Calendar类提供了一个获取日期在所属年份中是第几周的方法，对于上一年末的某一天
     * 和新年初的某一天在同一周内也一样可以处理，例如2012-12-31和2013-01-01虽然在
     * 不同的年份中，但是使用此方法依然判断二者属于同一周内
     * </pre>
     *
     * @param date
     * @return
     */
    public static boolean isSameWeekWithToday(Date date,Date date1) {

        if (date == null) {
            return false;
        }

        // 0.先把Date类型的对象转换Calendar类型的对象
        Calendar todayCal = Calendar.getInstance();
        Calendar dateCal = Calendar.getInstance();

        todayCal.setTime(date1);
        dateCal.setTime(date);

        // 1.比较当前日期在年份中的周数是否相同
        if (todayCal.get(Calendar.WEEK_OF_YEAR) == dateCal.get(Calendar.WEEK_OF_YEAR)) {
            return true;
        } else {
            return false;
        }
    }
}
