package com.svv.jys.code.module.business.tradechat.base.mychat;

/**
 * author：ajiang
 * mail：1025065158@qq.com
 * blog：http://blog.csdn.net/qqyanjiang
 */
public class ChatDataUtils {
    /**
     * Prevent class instantiation.
     */
    private ChatDataUtils() {
    }

    public static String getVolUnit(float num) {

        int e = (int) Math.floor(Math.log10(num));

        if (e >= 8) {
            return "亿手";
        } else if (e >= 4) {
            return "万手";
        } else {
            return "手";
        }


    }
}
