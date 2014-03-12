package common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import play.Logger;

public class AppProp {
	
	public static String localPropertiesFilePath = "properties/localhost.properties";
	public static String productionPropertiesFilePath = "properties/production.properties";
	public static String enI18nFilePath = "properties/en.i18n.properties";
	public static String cnI18nFilePath = "properties/cn.i18n.properties";
	
	private static Map<String, String> propLocalCache = new HashMap<String, String>();
	
	private static Properties loadProp(String path) {
		Properties prop = new Properties();
		try {
			prop.load(AppProp.class.getClassLoader().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return prop;
	}
	
	public static String getPropertyValue(String key){
		if (propLocalCache.get(key) == null) {
			Properties prop = loadProp(productionPropertiesFilePath);
			propLocalCache.put(key, prop.getProperty(key, null));	
		}
		return propLocalCache.get(key);
	}
	
	public static String getPropertyi18n(String key) {
		if (propLocalCache.get(key) == null) {
			Properties prop = loadProp(enI18nFilePath);
			propLocalCache.put(key, prop.getProperty(key, null));	
		}
		return propLocalCache.get(key);
	}

}
