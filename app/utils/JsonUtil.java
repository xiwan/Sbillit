package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;

public class JsonUtil {
	public long code;
	public Object body;
	
	public JsonUtil(long code, Object body){
		this.code = code;
		this.body = body;
	}
	
	public static JsonNode toJson(long code, Object body) {
		
        return Json.toJson(new JsonUtil(code, body));
    }
	
	public static JsonNode fromJson(String jsonStr){
		return Json.parse(jsonStr);
	}
	
	public static String stripQuot(String str){
		return str.replace("\"", "");
	}
}
