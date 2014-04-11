package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import play.mvc.Result;
import play.mvc.With;

import com.fasterxml.jackson.databind.JsonNode;

import common.Constant;
import entity.SbillitAds;
import entity.SbillitFeed;
import services.SbillitAdsAndFeedsService;
import utils.JsonUtil;

public class ModuleAdsAndFeeds extends Filter {

	@Autowired
	private SbillitAdsAndFeedsService sbillitAdsAndFeedsService;
	
	public Result pull(){
		JsonNode js = null;
		List<SbillitAds> adsList = sbillitAdsAndFeedsService.findValidAds();
		if (adsList == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, adsList);
		}
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result wall() {
		long ownerId = super.getUserBySessionId();
		JsonNode js = null;
		List<SbillitFeed> feedList = sbillitAdsAndFeedsService.findUserFeeds(ownerId);
		if (feedList == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, feedList);
		}
		return ok(js);
	}

}
