package utils;

public class Constant {
	
	public static long ERROR_INTERNAL = 500;
	public static long ERROR_BAD_REQUEST = 400;
	public static long ERROR_NOT_ALLOWED = 300;
	public static long ERROR_FREE = 200;
	public static long ERROR_SESSION_EXPIRED = 100;
	public static long ERROR_SESSION_NOT_EXIST = 101;
	public static long ERROR_SESSION_NORMAL = 102;
	
	public static long LOGIN_UNKONW = 0;
	public static long LOGIN_PHONE = 1;
	public static long LOGIN_WECHAT = 2;
	public static long LOGIN_QQ = 3;
	public static long LOGIN_WEIBO = 4;
	
	public static String USER_PHONE_NOT_EXIST = "user.phone.not.exist";
	public static String USER_PHONE_DUPLICATE = "user.phone.duplicate";
	public static String USER_SMSTOKEN_NOT_MATCH = "user.smstoken.not.match";
	public static String USER_SMSTOKEN_EXPIRED = "user.smstoken.expired";
	public static String USER_NORMAL = "user.normal";
	
	public static String SESSION_EXPIRED = "session.expired";
	public static String SESSION_NOT_EXIST = "session.not.exist";
	public static String SESSION_NORMAL = "session.normal";
	
	
}
