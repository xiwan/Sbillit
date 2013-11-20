package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import play.mvc.Controller;
import play.mvc.Result;
import services.SbillitUserService;

public class UserModule extends Controller {
	@Autowired
	private SbillitUserService sbillitUserService;
	
	public Result info(int id) {
		String userName = sbillitUserService.findUserById(id).getNickname();
    	return ok("the username is : " +  userName);
    }

}
