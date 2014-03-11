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
import services.SbillitCloopenSmsService;
import services.SbillitSessionService;
import services.SbillitUserService;
import utils.AppProp;
import utils.Constant;
import utils.JsonUtil;

public class ModuleUser extends Filter {
	@Autowired
	private SbillitUserService sbillitUserService;
	
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
	@Autowired
	private SbillitCloopenSmsService sbillitCloopenSmsService;
	
	@With(Interceptor.class)
	public Result info() {
		long userId = super.getUserBySessionId();
		SbillitUser user = sbillitUserService.findUserById(userId);		
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, user);
    	return ok( Json.toJson(js) );
    }
	
	public Result register(){
		// store the phone number and assign it with an expiring smsToken
		JsonNode postDataJson = super.parseParamJson("postData");
		String phone = postDataJson.get("phone").asText();
		Integer deviceType = postDataJson.get("deviceType").asInt();
		String nickname = phone;
		String deviceToken = "";
		String smsToken = "0000";
		if (deviceType == Constant.DEVICE_IOS) {
			deviceToken = postDataJson.get("deviceToken").asText();
			smsToken = sbillitUserService.createNewUserAndAssignSmsToken(phone, nickname, deviceType, deviceToken);
		}else if (deviceType == Constant.DEVICE_ANDROID) {
			
		}
		
		JsonNode js = null;
		System.out.println(AppProp.getPropertyi18n("sms.disabled"));
		if (AppProp.getPropertyi18n("sms.disabled") == "1") {
			js = JsonUtil.toJson(Constant.ERROR_FREE, smsToken);
		}else {
			try {
				String returnStr = sbillitCloopenSmsService.sendSmsToUser(phone, smsToken);
				if (returnStr != null) {
					if (smsToken.equals(Constant.USER_PHONE_DUPLICATE)){
						js = JsonUtil.toJson(Constant.ERROR_INTERNAL, "duplicate phone number!");
					}else {
						js = JsonUtil.toJson(Constant.ERROR_FREE, smsToken);
					}
				}else {
					js = JsonUtil.toJson(Constant.ERROR_INTERNAL, smsToken);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				js = JsonUtil.toJson(Constant.ERROR_INTERNAL, "boooo!");
			}
		}
		return ok(js);
	}
	
	public Result login(){
		
		// here we need to verify the 'phone' and 'token'
		// phone must be equal to last registered phone number
		// token should be valid, and match with last registered token

		JsonNode postDataJson = super.parseParamJson("postData");	
		String smsToken = postDataJson.get("token").asText(); 
		String phone = postDataJson.get("phone").asText();
		
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
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, AppProp.getPropertyi18n(key));
		}
		return ok(js);
	}


}
