package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AppProperties {
	
	public static String localPropertiesFilePath = "properties/localhost.properties";
	public static String productionPropertiesFilePaht = "properties/production.properties";
	
	private Properties loadProp() throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		prop.load(AppProperties.class.getClassLoader().getResourceAsStream(productionPropertiesFilePaht));	
		return prop;
	}
	
	public String getPropertyValue(String key) throws FileNotFoundException, IOException{
		Properties prop = loadProp();
		return prop.getProperty(key, null);
	}

}
