package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import common.AppProp;
import common.Constant;

import utils.DateUtil;

import dao.SbillitComboDao;
import dao.SbillitOrderDao;
import dao.SbillitUserSessionDao;

import entity.SbillitCombo;
import entity.SbillitOrder;
import entity.SbillitOrderComment;
import entity.SbillitOrderItem;
import entity.SbillitOrderShare;
import entity.SbillitOrderThumbup;
import entity.SbillitUser;
import entity.SbillitUserSession;

public class SbillitOrderServiceImpl implements SbillitOrderService {
	@Autowired
	private SbillitOrderDao sbillitOrderDao;
	@Autowired
	private SbillitUserSessionDao sbillitUserSessionDao;
	@Autowired
	private SbillitComboDao sbillitComboDao;
	
	@Override
	public List<SbillitOrder> findAllOrders() {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findAllOrders();
	}

	@Override
	public SbillitOrder findOrderbyId(long id) {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findOrderbyId(id);
	}

	@Override
	@Transactional
	public List<SbillitOrder> findOrderHistory(long userId, String session) {
		// TODO Auto-generated method stub
		SbillitUserSession userSession = sbillitUserSessionDao.getUserSessionByUserId(userId);
		List<SbillitOrder> orderList = new ArrayList<SbillitOrder>();
		if (userSession.getSession().equals(session)){
			orderList = sbillitOrderDao.findOrderHistoryByUserId(userId);
		}
		return orderList;
	}
	

	@Override
	public long createOrder(long ownerId, JsonNode orderShareArry, JsonNode orderItemArray,
			JsonNode orderCreator, String orderImagePath, String orderComments,
			String orderTitle, Double amount) {
		// TODO Auto-generated method stub
		long expiredAt = DateUtil.getExpiredTimeFromNow("order.endure");
		SbillitOrder order = new SbillitOrder();
		order.setUserId(ownerId);
		order.setType(Constant.ORDER_TYPE_NORMAL);
		order.setTitle(orderTitle);
		order.setAmount(amount);
		order.setStatus(Constant.ORDER_NA);
		order.setExpiredAt(expiredAt);
		sbillitOrderDao.createOrder(order);
		
		long orderId = order.getId();
		
		if (orderShareArry != null && orderShareArry.isArray()){
			for (JsonNode os: orderShareArry) {
				Long userId = os.get("userID").asLong();
				// throuth the userId, retrieve the device token and use apsn
				String phone = os.get("phoneNumber").asText();
				sbillitOrderDao.createOrderShare(orderId, phone, userId, Constant.ORDER_SHARE_AGREE);
			}			
		}
		
		if (orderItemArray != null && orderItemArray.isArray()){
			for (JsonNode oi: orderItemArray) {
				String itemName = oi.get("itemName").asText();
				JsonNode buyerArray = oi.get("buyerArray");
				Double itemPrice = oi.get("itemPrice").asDouble();
				Long itemNum = oi.get("itemTotalAmount").asLong();
				if (buyerArray != null && buyerArray.isArray()) {
					for (JsonNode buyer: buyerArray) {
						Long userId = buyer.get("buyer").get("userID").asLong();
						sbillitOrderDao.createOrderItem(orderId, userId, itemNum, itemPrice, itemName);
					}
				}
			}			
		}
		
		return orderId;
	}


	@Override
	public SbillitOrder quickOrder(long ownerId, String orderTitle, JsonNode orderShareArray, Double amount) {
		// TODO Auto-generated method stub		
		long expiredAt = DateUtil.getExpiredTimeFromNow("order.endure");
		
		SbillitOrder order = new SbillitOrder();
		order.setUserId(ownerId);
		order.setType(Constant.ORDER_TYPE_QUICK);
		order.setTitle(orderTitle);
		order.setAmount(amount);
		order.setStatus(Constant.ORDER_SHARED);
		order.setExpiredAt(expiredAt);
		sbillitOrderDao.createOrder(order);
		
		long orderId = order.getId();
		
		// create order share records		
 		// duplication check point make sure the fa and ca dont share same phonenumber
		if (orderShareArray.isArray()){
			for (JsonNode os: orderShareArray) {
				String phone = os.get("phoneNumber").asText();
				Long userId = os.get("userID").asLong();
				sbillitOrderDao.createOrderShare(orderId, phone, userId, Constant.ORDER_SHARE_NA);
			}			
		}else {
			
		}

//		if (contactsArray.isArray()){
//			for (JsonNode ca: contactsArray) {
//				String phone = ca.get("phoneNumber").asText();
//				sbillitOrderDao.createOrderShare(orderId, phone, 0l, Constant.ORDER_SHARE_NA);			
//			}			
//		}else  {
//			
//		}

		//sbillitOrderDao.findOrderbyId(order.getId());
		return order;
	}

	@Override
	public Map<Long, String> uploadFile(long orderId, String filePath) {
		// TODO Auto-generated method stub
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId);
		Map<Long, String> returnMap = new HashMap<Long, String>();
		if (order == null) {
			returnMap.put(Constant.ERROR_INTERNAL, Constant.ORDER_IMAGE_UPLOAD_FAILED_NO_ORDER);
		}else {
			returnMap.put(Constant.ERROR_FREE, Constant.ORDER_IMAGE_UPLOAD_SUCCESS);
			if (order.getPicture1() == null){
				sbillitOrderDao.updateOrder(orderId, null, filePath, null, null);
			}else if (order.getPicture2() == null){
				sbillitOrderDao.updateOrder(orderId, null, null, filePath, null);
			}else if (order.getPicture3() == null){
				sbillitOrderDao.updateOrder(orderId, null, null, null, filePath);
			}else {
				returnMap.put(Constant.ERROR_INTERNAL, Constant.ORDER_IMAGE_UPLOAD_FAILED_MAX);
			}
		}
		return returnMap;
	}

	@Override
	public void thumbup(Long orderId, Long userId, String title) {
		// TODO Auto-generated method stub
		sbillitOrderDao.createOrderThumbup(orderId, userId, title);
		List<SbillitOrderItem> orderItemList = sbillitOrderDao.findOrderItemByOrderId(orderId);
		SbillitCombo orderCombo = new SbillitCombo();
		orderCombo.setId(orderId); // set order id here
		// for loop to build combo data
		sbillitComboDao.createCombo(orderCombo);
	}

	@Override
	public Map<String, Object> favoriteList(Long userId) {
		// TODO Auto-generated method stub
		List<SbillitOrderThumbup> orderThumbupList = sbillitOrderDao.findOrderThumbupByUserId(userId);
		List<Long> ids = new ArrayList<Long>();
		for (SbillitOrderThumbup orderThumbup: orderThumbupList) {
			ids.add(orderThumbup.getOrderId());
		}
		List<SbillitOrder> orderList = sbillitOrderDao.findOrderInIds(ids);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("orderThumbupList", orderThumbupList);
		returnMap.put("orderList", orderList);
		
		return returnMap;
	}

	@Override
	public Map<String, Object> orderDetail(Long userId, Long orderId) {
		// TODO Auto-generated method stub
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId);
		List<SbillitOrderComment> orderCommentList = sbillitOrderDao.findOrderCommentByUserIdAndOrderId(userId, orderId);
		List<SbillitOrderShare> orderShareList = sbillitOrderDao.findOrderShareByUserIdAndOrderId(userId, orderId);
		List<SbillitOrderItem> orderItemList = sbillitOrderDao.findOrderItemByUserIdAndOrderId(userId, orderId);
		List<SbillitOrderThumbup> orderThumbupList = sbillitOrderDao.findOrderThumbupByUserIdAndOrderId(userId, orderId);
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("order", order);
		returnMap.put("orderCommentList", orderCommentList);
		returnMap.put("orderShareList", orderShareList);
		returnMap.put("orderItemList", orderItemList);
		returnMap.put("orderThumbupList", orderThumbupList);
		return returnMap;
	}

	@Override
	public long postComment(Long orderId, Long userId, Long atUserId,
			String message, Long status) {
		// TODO Auto-generated method stub
		
		long commentId = sbillitOrderDao.createOrderComment(orderId, userId, atUserId, message, status);
		return commentId;
	}

}
