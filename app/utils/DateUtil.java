package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

public class DateUtil {
	public static final String DATE_PATTERN = "yyyy-MM-dd";
	public static final String DATE_PATTERN2="yyyy.MM.dd HH:mm";
	public  static final String DATE_PATTERN3="yyyy-MM-dd HH:mm";
	public  static final String DATE_TIME_PATTERN="yyyy-MM-dd HH:mm:ss";
	public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String MARK_TODAY = "t";
	public static final String MARK_LAST_MONTH = "lm";
	public static final String MARK_THIS_MONTH = "tm";
	public static final String MARK_THIS_WEEK = "tw";
	public static final String MARK_LAST_WEEK = "lw";
	public static final String MARK_TODAY_TRADE = "tt";
	public static final String MARK_LAST_THREE_MONTH = "lm3";
	
	public static long GetCurrentTimeStamp(){
		return System.currentTimeMillis()/1000;
	}
	
	public static long getExpiredTimeFromNow(String key){
		long currentTimestamp = DateUtil.GetCurrentTimeStamp();
		long expiredTimestamp = 0l;
		try {
			expiredTimestamp = currentTimestamp + 
					Long.parseLong(AppProperties.getPropertyValue(key));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return expiredTimestamp;
	}

}
