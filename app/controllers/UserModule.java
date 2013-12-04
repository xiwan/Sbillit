package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUser;
import entity.SbillitUserAuthtoken;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.SbillitSessionService;
import services.SbillitUserService;

public class UserModule extends Controller {
	@Autowired
	private SbillitUserService sbillitUserService;
	
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
	public Result login(){
		String sessionId = null;
		try {
			sessionId = sbillitSessionService.sessionCheckAndHandle(6);
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
		return ok(sessionId);
	}
	
	public Result info(int id) {
		SbillitUser user = sbillitUserService.findUserById(id);
		//String userName = sbillitUserService.findUserById(id).getNickname();
		//ObjectNode result = Json.newObject();
    	return ok( Json.toJson(user) );
    }

}
