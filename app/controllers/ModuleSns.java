package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import common.AppProp;
import common.Constant;

import entity.SbillitFriend;

import play.mvc.Result;
import play.mvc.With;

import services.SbillitSnsService;
import utils.JsonUtil;

public class ModuleSns  extends Filter {
	
	@Autowired
	private SbillitSnsService sbillitSnsService;
	
	@With(Interceptor.class)
	public Result get(Long friendId) {
		long userId = super.getUserBySessionId();
		List<SbillitFriend> friendList = sbillitSnsService.findFriends(userId, (friendId==0)?null:friendId);
		JsonNode js = null;
		js = JsonUtil.toJson(Constant.ERROR_FREE, friendList);
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result add() {
		long userId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		JsonNode friendArr = postDataJson.get("friendArray");
		long status = sbillitSnsService.addFriends(userId, friendArr, Constant.SNS_NORMAL);
		JsonNode js = null;
		if (status == 0) {
			js = JsonUtil.toJson(Constant.ERROR_FREE, AppProp.getPropertyi18n(Constant.SNS_FRIEND_ADD_SUCCESS));
		}else if (status == 1) {
			js = JsonUtil.toJson(Constant.ERROR_FREE, AppProp.getPropertyi18n(Constant.SNS_FRIEND_ADD_FAILURE_EXISTING));
		}else {
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, AppProp.getPropertyi18n(Constant.SNS_FRIEND_ADD_FAILURE_ERROR));
		}
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result block() {
		long userId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		long friendId = postDataJson.get("friendId").asLong();
		sbillitSnsService.updateFriend(userId, friendId, Constant.SNS_BLOCK);
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result restore() {
		long userId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		long friendId = postDataJson.get("friendId").asLong();
		sbillitSnsService.updateFriend(userId, friendId, Constant.SNS_NORMAL);
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result delete() {
		long userId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		long friendId = postDataJson.get("friendId").asLong();
		sbillitSnsService.updateFriend(userId, friendId, Constant.SNS_DELTE);
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		return ok(js);
	}
	
}
