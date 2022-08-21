package jmu.zsw.utils;

import org.apache.commons.lang3.time.FastDateFormat;

public abstract class TimeUtil {

    public static String format(Long timeStamp, String pattern){
        return FastDateFormat.getInstance(pattern).format(timeStamp);
    }

    public static void main(String[] args) {
        String format = TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd");
        System.out.println(format);
    }
}
