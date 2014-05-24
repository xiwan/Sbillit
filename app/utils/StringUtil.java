package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String PHONE_DELIMITER = "[+ (){}_-]";
	private static final String PHONE_PATTERN = "^[+0-9.()-]{10,25}$";
	private static final String PASSWORD_PATTERN = "^[+0-9.()-]{10,25}$";
	
	public static String phoneNormalize(String phone){
		Pattern p = Pattern.compile(PHONE_DELIMITER); 
		Matcher m = p.matcher(phone);
		String norPhone = m.replaceAll("");
		System.out.println(norPhone);
		return norPhone;
	}
	
	public static boolean phoneCheck(String phone) {
		Pattern p = Pattern.compile(PHONE_PATTERN);
	    Matcher m = p.matcher(phone);
	    return m.matches();
	}
	
	public static boolean passwordCheck(String password) {
		if (password.length() < 8 || password.length() > 27) {   
	        return false;  
	    } else {      
	        char c;   
	        for (int i = 0; i < password.length(); i++) {  
	            c = password.charAt(i);  
	            if (!Character.isLetterOrDigit(c)) {          
	                return false;  
	            }  
	        }   
	    }  
	    return true;  
	
//		Pattern p = Pattern.compile(PASSWORD_PATTERN);
//	    Matcher m = p.matcher(password);
//	    return m.matches();
	}
	
	public static boolean isNotBlank(String str){
        return str!=null&&!"".equals(str.trim())&&!"UNDEFINED".equals(str.trim());
    }
	
	public static String getExtension(String fileName){
		int dot = fileName.lastIndexOf(".");
		return fileName.substring(dot+1);
	}
	
	public static void main(String[] args){
		System.out.println(StringUtil.phoneCheck("(13800001111"));
	}

}
