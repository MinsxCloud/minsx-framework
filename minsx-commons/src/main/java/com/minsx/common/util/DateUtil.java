package com.minsx.common.util;

public class DateUtil {

    /**
     * 将毫秒转为秒,分钟,小时,天
     *
     * @param mss 毫秒
     * @return 天小时分钟秒
     */
    public static String formatMilliSecond(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;
        return (days > 0 ? (days + "天") : "") + (hours > 0 ? (hours + "时") : "") + (minutes > 0 ? (minutes + "分") : "")
                + (seconds + "秒");
    }

}
