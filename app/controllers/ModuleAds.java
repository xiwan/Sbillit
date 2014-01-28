package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitAds;

import services.SbillitAdsService;
import utils.Constant;
import utils.JsonUtil;

public class ModuleAds extends Filter {

	@Autowired
	private SbillitAdsService sbillitAdsService;
	
	public Result pull(){
		JsonNode js = null;
		List<SbillitAds> adsList = sbillitAdsService.findValidAds();
		if (adsList == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, adsList);
		}
		return ok(js);
	}

}
