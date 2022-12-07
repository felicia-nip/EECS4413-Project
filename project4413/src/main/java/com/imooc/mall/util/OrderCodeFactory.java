package com.imooc.mall.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Generate order No utility class
 */
public class OrderCodeFactory {
    /**
     * create a simple date by using current time
     */
    public static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmss");
        }
    };

    /**
     * transfer ThreadLocal<SimpleDateFormat> type obj to String
     * @return current time with String type.
     */
    private static String getDateTime() {
        DateFormat sdf = simpleDateFormatThreadLocal.get();
        return sdf.format(new Date());
    }


    private static int getRandom(Long n) {
        Random random = new Random(n);
        // Get 5-digit random number
        return (int) (random.nextDouble() * (90000)) + 10000;
    }

    public static String getOrderCode(Long userId) {
        return getDateTime() + getRandom(userId);
    }
}
