package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;

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
import utils.AppProp;
import utils.Constant;
import utils.DateUtil;
import utils.FileUtil;
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
	public Result normal(){
		JsonNode postDataJson = super.parseParamJson("postData");
		postDataJson.get("totalNumber").toString();
		postDataJson.get("memberArray").toString();
		postDataJson.get("orderCurrency").toString();
		
		return ok("");
	}

	@With(Intercept.class)
	public Result quick(){
		long ownerId = super.getUserBySessionId();
		JsonNode postDataJson = super.parseParamJson("postData");
		
		String orderTitle = postDataJson.get("orderTitle").asText();		
		Double totalNumber = postDataJson.get("totalNumber").asDouble();
		JsonNode friendsArray = postDataJson.findValue("friendsArray");
		JsonNode contactsArray = postDataJson.findValue("contactsArray");		

		SbillitOrder order = sbillitOrderService.quickOrder(ownerId, orderTitle, friendsArray, contactsArray, totalNumber);
		JsonNode js = JsonUtil.toJson(Constant.ERROR_FREE, order);
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
		    String newFilePath = AppProp.getPropertyValue("file.image.root.path") + "/" + orderId + 
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
		String newFilePath = AppProp.getPropertyValue("file.image.root.path") + "/" + orderId + 
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

}
