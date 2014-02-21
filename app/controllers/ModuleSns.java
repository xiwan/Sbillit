package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitFriend;

import play.mvc.Result;

import services.SbillitSnsService;
import utils.AppProp;
import utils.Constant;
import utils.JsonUtil;

public class ModuleSns  extends Filter {
	
	@Autowired
	private SbillitSnsService sbillitSnsService;
	
	public Result get(Long userId, Long friendId) {
		List<SbillitFriend> friendList = sbillitSnsService.findFriends(userId, (friendId==0)?null:friendId);
		JsonNode js = null;
		js = JsonUtil.toJson(Constant.ERROR_FREE, friendList);
		return ok(js);
	}
	
	public Result add(Long userId) {
		JsonNode postDataJson = super.parseParamJson("postData");	
		Long friendId = postDataJson.get("friendId").asLong(); 
		long status = sbillitSnsService.addFriends(userId, friendId, 0l);
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

}
