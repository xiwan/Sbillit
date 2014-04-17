package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

import common.Apns;
import common.AppProp;
import common.Constant;
import entity.SbillitAds;
import entity.SbillitCombo;
import entity.SbillitOrder;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Http.RawBuffer;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.With;
import services.SbillitOrderService;
import utils.DateUtil;
import utils.FileUtil;
import utils.JsonUtil;

public class ModuleOrder extends Filter {
	@Autowired
	private SbillitOrderService sbillitOrderService;

	public Result info(Long id) {
		SbillitOrder order = sbillitOrderService.findOrderbyId(id); 
		return ok(Json.toJson(order));
	}
	
	@With(Interceptor.class)
	public Result history(long userId){
		String session = session().get("session");
		long ownerId = super.getUserBySessionId();
		List<SbillitOrder> orderList = sbillitOrderService.findOrderHistory(ownerId, session);
		JsonNode js = null;
		if (orderList == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, orderList);
		}
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result detail(long orderId){
		long ownerId = super.getUserBySessionId();
		Map returnMap = sbillitOrderService.orderDetail(ownerId, orderId);
		JsonNode js = null;
		if (returnMap == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "booooo");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, returnMap);
		}
		return ok(js);
	}
	
	
	@With(Interceptor.class)
	public Result comment(long orderId){
		long ownerId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		String message = postDataJson.get("comment").asText();	
		Long atUserId = null;
		if (postDataJson.get("atUserId") != null) {
			atUserId = postDataJson.get("atUserId").asLong();
		}
		Long commentId = sbillitOrderService.postComment(orderId, ownerId, atUserId, message, Constant.ORDER_COMMENT_NORMAL);
		JsonNode js = null;
		if (commentId == null || commentId == 0){
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, "booooo");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, commentId);
		}
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result normal(){
		long ownerId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		JsonNode orderShareArry = postDataJson.get("orderShareArray");
		JsonNode orderItemArray = postDataJson.get("orderItemArray");
		JsonNode orderCreator = postDataJson.get("orderCreator");
		String orderImagePath = "";
		if (postDataJson.get("orderImagePath") != null){
			orderImagePath = postDataJson.get("orderImagePath").asText();
		}
		String orderComments = "";
		if (postDataJson.get("orderComments") != null){
			orderComments = postDataJson.get("orderComments").asText();
		}
		String orderTitle = postDataJson.get("orderTitle").asText();
		Double totalNumber = postDataJson.get("totalNumber").asDouble();
		
		long orderId = sbillitOrderService.createOrder(ownerId, orderShareArry, orderItemArray, orderCreator, 
				orderImagePath, orderComments, orderTitle, totalNumber);
		
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, orderId);
		return ok(js);
	}

	@With(Interceptor.class)
	public Result quick(){
		long ownerId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		
		String orderTitle = postDataJson.get("orderTitle").asText();		
		Double totalNumber = postDataJson.get("totalNumber").asDouble();
		JsonNode orderShareArray = postDataJson.findValue("orderShareArray");
		//JsonNode friendsArray = postDataJson.findValue("friendsArray");
		//JsonNode contactsArray = postDataJson.findValue("contactsArray");		

		SbillitOrder order = sbillitOrderService.quickOrder(ownerId, orderTitle, orderShareArray, totalNumber);
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, order.getId());
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result modify(Long orderId){
		long ownerId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		JsonNode orderItemArray = postDataJson.get("orderItemArray");
		Double totalAmount = postDataJson.get("totalNumber").asDouble();
		long retId = sbillitOrderService.modifyOrderItem(orderId, ownerId, totalAmount, orderItemArray);
		JsonNode js = null;
		if (retId == 0) {
			js = JsonUtil.toJson(Constant.ERROR_NOT_ALLOWED, "not allowed");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, "yaaaa");
		}
		return ok(js);
	}
	
	public Result uploadSync(long orderId) {
		MultipartFormData body = request().body().asMultipartFormData();
		FilePart picture = body.getFile("QuickOrder_Image");
		JsonNode js = null;
		if (picture != null) {
		    String fileName = picture.getFilename();
		    String contentType = picture.getContentType(); 
		    File file = picture.getFile(); 
		    String tempFilePath = file.getPath();
		    String newFilePath = AppProp.getPropertyValue("file.image.root.path") + "/order/" + orderId + 
					"-" + DateUtil.GetCurrentTimeStamp() + "-" + fileName;
		    FileUtil.move(tempFilePath, newFilePath);
		    
		    Map<Long, String> returnMap = sbillitOrderService.uploadFile(orderId, newFilePath);
		    if (returnMap.get(Constant.ERROR_INTERNAL) != null) {
				js = JsonUtil.toJson(Constant.ERROR_INTERNAL, 
						AppProp.getPropertyi18n(returnMap.get(Constant.ERROR_INTERNAL)));
			}else if (returnMap.get(Constant.ERROR_FREE) != null){
				js = JsonUtil.toJson(Constant.ERROR_FREE, 
						AppProp.getPropertyi18n(Constant.ORDER_IMAGE_UPLOAD_SUCCESS));
			}
		}  
		return ok(js);
	}
	
	//@With(Intercept.class)
	public Result upload(long orderId){
		// check the order id does exist
		RequestBody body = request().body();
		RawBuffer buf = body.asRaw();
		File file = buf.asFile();
		String tempFilePath = file.getPath();
		String fileName = file.getName();
		String newFilePath = AppProp.getPropertyValue("file.image.root.path") + "/order/" + orderId + 
				"-" + DateUtil.GetCurrentTimeStamp() + "-" + fileName;
		FileUtil.move(tempFilePath, newFilePath);
		JsonNode js = null;
		Map<Long, String> returnMap = sbillitOrderService.uploadFile(orderId, newFilePath);	
		if (returnMap.get(Constant.ERROR_INTERNAL) != null) {
			js = JsonUtil.toJson(Constant.ERROR_INTERNAL, 
					AppProp.getPropertyi18n(returnMap.get(Constant.ERROR_INTERNAL)));
		}else if (returnMap.get(Constant.ERROR_FREE) != null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, 
					AppProp.getPropertyi18n(Constant.ORDER_IMAGE_UPLOAD_SUCCESS));
		}
	
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result thumbup(Long orderId) {
		long ownerId = 1;
		RequestBody body = request().body();
		JsonNode postDataJson = super.parseParamJson("postData");
		String title = postDataJson.get("title").asText();
		long retId = sbillitOrderService.thumbup(orderId, ownerId, title);
		JsonNode js = null;
		if (retId == 0) {
			js = JsonUtil.toJson(Constant.ERROR_NOT_ALLOWED, "not allowed");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, "yaaaa");
		}
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result favorite(){
		long ownerId = super.getUserBySessionId();
		Map returnMap = sbillitOrderService.favoriteList(ownerId);
		JsonNode js = null;
		if (returnMap == null){
			js = JsonUtil.toJson(Constant.ERROR_FREE, "booooo");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, returnMap);
		}
		return ok(js);
	}
	
	@With(Interceptor.class)
	public Result close(Long orderId){
		long ownerId = super.getUserBySessionId();
		long retId = sbillitOrderService.closeOrder(orderId, ownerId);
		JsonNode js = null;
		if (retId == 0) {
			js = JsonUtil.toJson(Constant.ERROR_NOT_ALLOWED, "not allowed");
		}else {
			js = JsonUtil.toJson(Constant.ERROR_FREE, "yaaaa");
		}
		return ok(js);
	}


}
