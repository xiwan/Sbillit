package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitUserAuthtoken;

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
import utils.Constant;
import utils.JsonUtil;


public class Intercept extends Action.Simple {
	
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
	@Override
	public Promise<SimpleResult> call(Context ctx) throws Throwable {
		// TODO Auto-generated method stub
		// each request should come with a session id
		String sessionId = ctx.request().getQueryString("sessionId");
		String uri = ctx.request().uri();
		JsonNode js = null;
		if (sessionId == null){
			// need to login again
			
			if (uri.indexOf("/user/login") != -1){
			    //js = JsonUtil.toJson(Constant.ERROR_AUTH_NO, "NeedSession");
				ctx.session().put("sessionId", "");
			}else{
				js = JsonUtil.toJson(Constant.ERROR_BAD_REQUEST, "BadRequest");
			}
		}else{
			// session check
			String parpareReturn = null;
			try {
				parpareReturn = sbillitSessionService.sessionCheckAndHandle(sessionId);
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
			if (parpareReturn.equals( SbillitUserAuthtoken.AUTHTOKEN_EXPIRED+"")){
				js = JsonUtil.toJson(Constant.ERROR_AUTH_EXPIRED, "AUTHTOKEN_EXPIRED");
			}else if (parpareReturn.equals( SbillitUserAuthtoken.AUTHTOKEN_NOT_EXIST+"")){
				js = JsonUtil.toJson(Constant.ERROR_AUTH_NO, "AUTHTOKEN_NOT_EXIST");
			}

			ctx.session().put("sessionId", parpareReturn);
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