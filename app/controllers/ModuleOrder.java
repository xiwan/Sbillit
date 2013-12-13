package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitOrder;

import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.SbillitOrderService;
import utils.Constant;
import utils.JsonUtil;

public class ModuleOrder extends Application {
	@Autowired
	private SbillitOrderService sbillitOrderService;
	
	public Result info(Long id) {
		SbillitOrder order = sbillitOrderService.findOrderbyId(id);
		
		return ok(Json.toJson(order));
	}
	
	@With(Intercept.class)
	public Result history(long userId){
		String session = session().get("session");
		List<SbillitOrder> orderList = sbillitOrderService.findOrderHistory(userId, session);
		JsonNode js = null;
		if (orderList == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, orderList);
		}
		return ok(js);
	}
	
	@With(Intercept.class)
	public Result create(){
		JsonNode postDataJson = super.parseParamJson("postData");
		postDataJson.get("totalNumber").toString();
		postDataJson.get("memberArray").toString();
		postDataJson.get("orderCurrency").toString();
		
		return ok("");
	}
	
	@With(Intercept.class)
	public Result quick(){
		long userId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
//		postDataJson.get("totalNumber").toString();
//		postDataJson.get("memberArray").toString();
//		postDataJson.get("orderCurrency").toString();
		SbillitOrder order = sbillitOrderService.quickOrder(userId, 10.01f);
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, order);
		return ok(js);
	}

}
