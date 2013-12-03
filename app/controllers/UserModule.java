package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUser;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.SbillitUserService;

public class UserModule extends Controller {
	@Autowired
	private SbillitUserService sbillitUserService;
	
	public Result info(int id) {
		SbillitUser user = sbillitUserService.findUserById(id);
		//String userName = sbillitUserService.findUserById(id).getNickname();
		//ObjectNode result = Json.newObject();
    	return ok( Json.toJson(user) );
    }

}
