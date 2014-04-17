package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	private static final String PHONE_DELIMITER = "[+ (){}_-]";
	
	public static String phoneNormalize(String phone){
		Pattern p = Pattern.compile(PHONE_DELIMITER); 
		Matcher m = p.matcher(phone); 
		return m.replaceAll("");
	}
	
	public static boolean isNotBlank(String str){
        return str!=null&&!"".equals(str.trim())&&!"UNDEFINED".equals(str.trim());
    }
	
	public static String getExtension(String fileName){
		int dot = fileName.lastIndexOf(".");
		return fileName.substring(dot+1);
	}
	
	public static void main(String[] args){
		System.out.println(StringUtil.phoneNormalize("(+86-182_2111 4531"));
	}

}
