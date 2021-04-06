package com.urms.utils;

import com.urms.handle.BusinessException;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class DateUtil {
    static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static Date covertTime(Date date){
        Timestamp now =new Timestamp(date.getTime());
        String str = df.format(now);
        Date newDate = null;
        try {
            newDate = df.parse(str);
        } catch (ParseException e) {
            throw new BusinessException(1,"转换日期去掉毫秒异常");
        }
        return newDate;


    }
    public static Date parseStrToDate(String strTime, String timeFromat,
                                      Date defaultValue) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.parse(strTime);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
