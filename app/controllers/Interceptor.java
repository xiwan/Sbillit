package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import common.AppProp;
import common.Constant;

import play.Logger;
import play.libs.F;
import play.libs.Json;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Results;
import play.mvc.SimpleResult;
import play.mvc.Http.Context;
import play.mvc.Http.RequestBody;
import services.SbillitSessionService;
import utils.JsonUtil;


public class Interceptor extends Action.Simple {
	
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
	@Override
	public Promise<SimpleResult> call(Context ctx) throws Throwable {
		// TODO Auto-generated method stub
		// each request should come with a session id
		String session = ctx.request().getQueryString("session");
		JsonNode js = null;
		if (session == null){
			// need to login again
			js = JsonUtil.toJson(Constant.ERROR_BAD_REQUEST, "BadRequest");
		}else{
			// session check
			String parpareReturn = sbillitSessionService.sessionCheckAndHandle(session);
			if (parpareReturn.equals( Constant.SESSION_EXPIRED )){
				js = JsonUtil.toJson(Constant.ERROR_SESSION_EXPIRED, 
						AppProp.getPropertyi18n(Constant.SESSION_EXPIRED));
			}else if (parpareReturn.equals( Constant.SESSION_NOT_EXIST )){
				js = JsonUtil.toJson(Constant.ERROR_SESSION_EXPIRED, 
						AppProp.getPropertyi18n(Constant.SESSION_NOT_EXIST));
			}

			ctx.session().put("session", parpareReturn);
		}
		if (js != null) {
			return badRequestResult(js);
		}else {
			return delegate.call(ctx);
		}   
	}
	
	public Promise<SimpleResult> badRequestResult(JsonNode jsonNode){
		return F.Promise.pure((SimpleResult) Results.badRequest(jsonNode));
	}
	
	public Promise<SimpleResult> okRequestResult(JsonNode jsonNode){
		return F.Promise.pure((SimpleResult) Results.ok(jsonNode));
	}

}