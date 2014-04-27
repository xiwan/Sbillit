package controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import common.AppProp;
import common.CloopenSms;
import common.Constant;
import entity.SbillitDice;
import entity.SbillitUser;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.Context;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.RequestBody;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.mvc.With;
import services.SbillitCloopenSmsService;
import services.SbillitMasterService;
import services.SbillitSessionService;
import services.SbillitUserService;
import utils.DateUtil;
import utils.FileUtil;
import utils.JsonUtil;
import utils.StringUtil;

public class ModuleUser extends Filter {
	@Autowired
	private SbillitUserService sbillitUserService;
	
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
	@Autowired
	private SbillitMasterService sbillitMasterService;
	
	
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
		String phone = "";
		try{
			phone = StringUtil.phoneNormalize(postDataJson.get("phone").asText());
		}catch (Exception e) {
			e.printStackTrace();
			return badRequest(JsonUtil.toJson(Constant.ERROR_INTERNAL, "not qualified phone number"));
		}
		Integer deviceType = postDataJson.get("deviceType").asInt();
		String nickname = phone;
		String deviceToken = "";
		if (deviceType == Constant.DEVICE_IOS) {
			deviceToken = postDataJson.get("deviceToken").asText();
		}else if (deviceType == Constant.DEVICE_ANDROID) {
			
		}
		String smsToken = sbillitUserService.createNewUserAndAssignSmsToken(phone, nickname, deviceType, deviceToken);
		JsonNode js = null;
		if (smsToken.equals(Constant.USER_PHONE_DUPLICATE)) {
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, AppProp.getPropertyi18n(smsToken));
		}else if (smsToken.equals(Constant.USER_SMSTOKEN_INERNAL_ERROR)){
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, smsToken);
		}else if (smsToken.equals(Constant.USER_SMSTOKEN_PROVIDER_ERROR)){
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, smsToken);
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, smsToken);
		}
		
		return ok(js);
	}
	
	public Result login(){
		
		// here we need to verify the 'phone' and 'token'
		// phone must be equal to last registered phone number
		// token should be valid, and match with last registered token

		JsonNode postDataJson = super.parseParamJson("postData");	
		String phone = "";
		try{
			phone = StringUtil.phoneNormalize(postDataJson.get("phone").asText());
		}catch (Exception e) {
			e.printStackTrace();
			return badRequest(JsonUtil.toJson(Constant.ERROR_INTERNAL, "not qualified phone number"));
		}
		String smsToken = postDataJson.get("token").asText(); 
		
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
	
	@With(Interceptor.class)
	public Result profileUpdate(){
		long id = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");	
		String name = postDataJson.get("name").asText(); 
		Long userId = sbillitUserService.updateUserName(id, name);
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, userId);
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result avatarUpdate(){
		long userId = super.getUserBySessionId();
		
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("avatar");
		
		JsonNode js = null;
		if (picture != null) {
		    String fileName = picture.getFilename();
		    //String ext = StringUtil.getExtension(fileName);
		    //String contentType = picture.getContentType(); 
		    File file = picture.getFile(); 
		    String tempFilePath = file.getPath();
		    String newFilePath =  "/avatar/" + userId + 
					"-" + DateUtil.GetCurrentTimeStamp() + "-" + fileName;
		    FileUtil.move(tempFilePath, AppProp.getPropertyValue("file.image.root.path") + newFilePath);
		    
		    long uid = sbillitUserService.updateAvatar(userId, tempFilePath,  newFilePath);
		    if (uid != userId) {
		    	js = JsonUtil.toJson(Constant.ERROR_INTERNAL, AppProp.getPropertyi18n(Constant.AVATAR_IMAGE_UPLOAD_FAILDED));
		    }else {
		    	js = JsonUtil.toJson(Constant.ERROR_FREE, newFilePath);
		    }
		}else {
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, AppProp.getPropertyi18n(Constant.AVATAR_IMAGE_UPLOAD_FAILDED));
		}
		
		return ok(js);
	}
	
    public Result version(Long versionId) {
    	Map<String, Object> masterData = new HashMap<String, Object>();
    	
    	String version = sbillitMasterService.getMasterVersion();
    	Long _versionId = Long.parseLong(version);
    	JsonNode js = null;
    	if (versionId<_versionId){
    		// get new master data
    		List<SbillitDice> dices = sbillitMasterService.getDice();
    		masterData.put("version", version);
    		masterData.put("dices", dices);
    	}else{
    		// no master data update
    		masterData.put("version", version);
    		
    	}
    	js = JsonUtil.toJson(Constant.ERROR_FREE, masterData);
    	return ok(js);
    }
	
	
    public static void main(String[] args){
    	String test = "abc.jpeg";
    	int dot = test.lastIndexOf(".");
    	System.out.println(test.substring(dot+1));
    }

}
