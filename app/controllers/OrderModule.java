package controllers;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitOrder;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.SbillitOrderService;

public class OrderModule extends Controller {
	@Autowired
	private SbillitOrderService sbillitOrderService;
	
	public Result info(int id) {
		SbillitOrder order = sbillitOrderService.findOrderbyId(id);
		
		return ok(Json.toJson(order));
	}

}
