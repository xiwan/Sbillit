package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitOrder;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.SbillitOrderService;

public class OrderModule extends Controller {
	@Autowired
	private SbillitOrderService sbillitOrderService;
	
	public Result info(int id) {
		SbillitOrder order = sbillitOrderService.findOrderbyId(id);
		
		return ok(Json.toJson(order));
	}
	
	@With(Intercept.class)
	public Result history(long userId){
		String sessionId = session().get("sessionId");
		List<SbillitOrder> orderList = sbillitOrderService.findOrderHistory(userId, sessionId);
		if (orderList == null){
			return ok("");
		}
		return ok(Json.toJson(orderList));
	}

}
