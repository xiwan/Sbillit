package common;

public class Constant {
	
	public static int DEVICE_IOS = 0;
	public static int DEVICE_ANDROID = 1;
	public static int DEVICE_WINPHONE = 2;
	
	public static long ERROR_INTERNAL = 500l;
	public static long ERROR_BAD_REQUEST = 400l;
	public static long ERROR_NOT_ALLOWED = 300l;
	public static long ERROR_FREE = 200l;
	public static long ERROR_SESSION_EXPIRED = 100l;
	public static long ERROR_SESSION_NOT_EXIST = 101l;
	public static long ERROR_SESSION_NORMAL = 102l;
	
	public static long LOGIN_UNKONW = 0l;
	public static long LOGIN_PHONE = 1l;
	public static long LOGIN_WECHAT = 2l;
	public static long LOGIN_QQ = 3l;
	public static long LOGIN_WEIBO = 4l;
	
	public static int ORDER_TYPE_NORMAL = 0;
	public static int ORDER_TYPE_QUICK = 1;
	
	public static int ORDER_NA = 0;
	public static int ORDER_CREATED = 1;
	public static int ORDER_SHARED = 2;
	public static int ORDER_EXPIRING = 3;
	public static int ORDER_EXPIRED = 4;
	public static int ORDER_FAILDED = 5;
	public static int ORDER_SUCCESS = 6;
	
	public static int ORDER_SHARE_NA = 0;
	public static int ORDER_SHARE_AGREE = 1;
	public static int ORDER_SHARE_REJECT = 2;
	
	public static long ORDER_COMMENT_NORMAL = 0;
	public static long ORDER_COMMENT_PRIVATE = 1;
	
	public static long SNS_NORMAL = 0;
	public static long SNS_BLOCK = 1;
	public static long SNS_DELTE = 2;
	
	public static String SNS_FRIEND_ADD_SUCCESS = "sns.friend.add.success";
	public static String SNS_FRIEND_ADD_FAILURE_EXISTING = "sns.friend.add.failure.existing";
	public static String SNS_FRIEND_ADD_FAILURE_ERROR = "sns.friend.add.failure.error";
	
	public static String ORDER_QUICK_TITLE = "order.quick.title";
	
	public static String ORDER_IMAGE_UPLOAD_SUCCESS="order.image.upload.success";
	public static String ORDER_IMAGE_UPLOAD_FAILED="order.image.upload.failed";
	public static String ORDER_IMAGE_UPLOAD_FAILED_MAX="order.image.upload.failed.max";
	public static String ORDER_IMAGE_UPLOAD_FAILED_NO_ORDER="order.image.upload.failed.no.order";
	public static String ORDER_IMAGE_UPLOAD_DOING="order.image.upload.doing";

	public static String USER_PHONE_NOT_EXIST = "user.phone.not.exist";
	public static String USER_PHONE_DUPLICATE = "user.phone.duplicate";
	public static String USER_SMSTOKEN_NOT_MATCH = "user.smstoken.not.match";
	public static String USER_SMSTOKEN_EXPIRED = "user.smstoken.expired";
	public static String USER_SMSTOKEN_INERNAL_ERROR = "user.smstoken.internal.error";
	public static String USER_SMSTOKEN_PROVIDER_ERROR = "user.smstoken.provider.error";
	public static String USER_NORMAL = "user.normal";
	
	public static String SESSION_EXPIRED = "session.expired";
	public static String SESSION_NOT_EXIST = "session.not.exist";
	public static String SESSION_NORMAL = "session.normal";
	
	
	
}
