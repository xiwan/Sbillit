package controllers;

import play.*;
import play.mvc.*;
import services.SbillitUserService;

import org.springframework.beans.factory.annotation.Autowired;


public class Application extends Controller {
	
	@Autowired
	private SbillitUserService sbillitUserService;

    public Result index() {
    	return ok("Your new application is ready.");
    }
    
    public Result autchCheck(){
    	String sessionId = session().get("sessionId");
    
    	return ok("");
    }

}
