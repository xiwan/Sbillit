package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitUser;
import entity.SbillitUserAuthtoken;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.With;
import services.SbillitSessionService;
import services.SbillitUserService;
import utils.Constant;
import utils.JsonUtil;

public class UserModule extends Controller {
	@Autowired
	private SbillitUserService sbillitUserService;
	
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
	@With(Intercept.class)
	public Result login(){
		// RequestBody body = request().body();
		// parse the session id, if no session id, this user is a new one
		String sessionId = session().get("sessionId");
		JsonNode js = null;
		long lastId = 0;
		if (sessionId == ""){
			// register a new user and session
			lastId = sbillitUserService.createUser("password", 0, 0, 0, 0, 0, "nickname");
			try {
				sessionId = sbillitSessionService.createSession(lastId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				js = JsonUtil.toJson(Constant.ERROR_INTERNAL, "NumberFormatException");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				js = JsonUtil.toJson(Constant.ERROR_INTERNAL, "FileNotFoundException");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				js = JsonUtil.toJson(Constant.ERROR_INTERNAL, "IOException");
			}
		}
		
		if (js != null) {
			return badRequest(js);
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, sessionId);
			return ok(js);			
		}

	}
	
	public Result info(int id) {
		SbillitUser user = sbillitUserService.findUserById(id);
    	return ok( Json.toJson(user) );
    }

}
