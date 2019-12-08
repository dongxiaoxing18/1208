package com.bawei.cms.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeRandomUtil {
	
	/**
	 * 随机生成一个固定时间段内的日期
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static Date randomDate(String beginDate,String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);
            
            if(start.getTime() >= end.getTime()){
                return null;
            }
            
            long date = random(start.getTime(),end.getTime());
            
            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
	
    
	/**
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
}
