package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

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
		long lastId = 0;
		if (sessionId == ""){
			// register a new user and session
			lastId = sbillitUserService.createUser("password", 0, 0, 0, 0, 0, "nickname");
			try {
				sessionId = sbillitSessionService.createSession(lastId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ok(lastId + " " + sessionId);
	}
	
	public Result info(int id) {
		SbillitUser user = sbillitUserService.findUserById(id);
    	return ok( Json.toJson(user) );
    }

}
