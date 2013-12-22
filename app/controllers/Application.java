package controllers;

import java.util.Map;

import play.*;
import play.libs.Json;
import play.mvc.*;
import services.SbillitSessionService;
import services.SbillitUserService;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitUserSession;


public class Application extends Controller {
	
	@Autowired
	private SbillitUserService sbillitUserService;
	
	@Autowired
	private SbillitSessionService sbillitSessionService;

    public Result index() {
    	return ok("Your new application is ready.");
    }
    
    public Result autchCheck(){
    	String sessionId = session().get("sessionId");
    	//sbillitUserAuthtokeService.ge
    	
    	return ok("");
    }
    
    protected long getUserBySessionId(){
    	String session = session().get("session");
    	SbillitUserSession userSession = sbillitSessionService.getUserSessionBySession(session); 
    	return userSession.getUserId(); 	
    }
    
    public String parseParam(String param){
    	Map<String, String[]> values = request().body().asFormUrlEncoded();
    	return values.get(param)[0];
    }
    
    public JsonNode parseParamJson(String param) {
    	Map<String, String[]> values = request().body().asFormUrlEncoded();
    	return Json.parse(values.get(param)[0]);
    }
    
//    public static void main(String[] args){
//    	JsonNode js = Json.parse("{\"phone\":\"13818180795\"}");
//    	System.out.println(js.findValue("phone").asLong());
//    }

}
