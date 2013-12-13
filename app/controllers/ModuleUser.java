package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitUser;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.With;
import services.SbillitSessionService;
import services.SbillitUserService;
import utils.AppProperties;
import utils.Constant;
import utils.JsonUtil;

public class ModuleUser extends Application {
	@Autowired
	private SbillitUserService sbillitUserService;
	
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
	public Result register(){
		// store the phone number and assign it with an expiring smsToken
		JsonNode postDataJson = super.parseParamJson("postData");
		//String phone = postDataJson.get("phone").toString();
		long phone = 0l;
		String nickname = postDataJson.get("phone").toString();
		
		String smsToken = sbillitUserService.createNewUserAndAssignSmsToken(phone, nickname);
		
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, smsToken);
		return ok(js);
	}
	
	public Result login(){
		
		// here we need to verify the 'phone' and 'token'
		// phone must be equal to last registered phone number
		// token should be valid, and match with last registered token

		JsonNode postDataJson = super.parseParamJson("postData");	
		String smsToken = postDataJson.get("token").toString(); 
		//String phone = postDataJson.get("phone").toString();
		long phone = 1l;
		
		JsonNode js = null;
		// register a new user and session
		Map returnMap = sbillitUserService.completeNewUserRegister(phone, smsToken);
		if (returnMap.get(Constant.USER_NORMAL) != null) {
			SbillitUser user = (SbillitUser) returnMap.get(Constant.USER_NORMAL);
			String session = sbillitSessionService.createSession(user.getId());
			user.setSession(session);
			js = JsonUtil.toJson(Constant.ERROR_FREE, user);
		}else {
			String key = "";
			if (returnMap.get(Constant.USER_PHONE_DUPLICATE) != null){
				key = Constant.USER_PHONE_DUPLICATE;
			}
			if (returnMap.get(Constant.USER_PHONE_NOT_EXIST) != null) {
				key = Constant.USER_PHONE_NOT_EXIST;
			}
			if (returnMap.get(Constant.USER_SMSTOKEN_EXPIRED) != null) {
				key = Constant.USER_SMSTOKEN_EXPIRED;
			}
			if (returnMap.get(Constant.USER_SMSTOKEN_NOT_MATCH) != null) {
				key = Constant.USER_SMSTOKEN_NOT_MATCH;
			}
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, AppProperties.getPropertyi18n(key));
		}
		return ok(js);
	}
	
	public Result info(Long id) {
		SbillitUser user = sbillitUserService.findUserById(id);
    	return ok( Json.toJson(user) );
    }

}
