package services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import common.AppProp;
import common.Apns;
import common.Constant;

import services.SbillitOrderService;
import utils.DateUtil;
import utils.StringUtil;

import dao.SbillitComboDao;
import dao.SbillitFeedDao;
import dao.SbillitOrderDao;
import dao.SbillitUserDao;
import dao.SbillitUserSessionDao;

import entity.SbillitCombo;
import entity.SbillitFeed;
import entity.SbillitOrder;
import entity.SbillitOrderComment;
import entity.SbillitOrderItem;
import entity.SbillitOrderShare;
import entity.SbillitOrderThumbup;
import entity.SbillitUser;
import entity.SbillitUserSession;

@Transactional
public class SbillitOrderServiceImpl implements SbillitOrderService {
	@Autowired
	private SbillitOrderDao sbillitOrderDao;
	@Autowired
	private SbillitUserSessionDao sbillitUserSessionDao;
	@Autowired
	private SbillitComboDao sbillitComboDao;
	@Autowired
	private SbillitUserDao sbillitUserDao;
	@Autowired
	private SbillitFeedDao sbillitFeedDao;
	
	private static int NA_ORDER = 0;
	private static int VALID_ORDER = 1;
	private static int EXPIRED_ORDER = 2;
	private static int FAILED_ORDER = 3;
	private static int CLOSED_ORDER = 4;
	
	private static long POINT_ORDER = 10l;
	
	@Override
	public List<SbillitOrder> findAllOrders() {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findAllOrders();
	}

	@Override
	public SbillitOrder findOrderbyId(long id) {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findOrderbyId(id, NA_ORDER);
	}

	@Override
	public List<SbillitOrder> findOrderHistory(long userId, String session) {
		// TODO Auto-generated method stub
		SbillitUserSession userSession = sbillitUserSessionDao.getUserSessionByUserId(userId);
		List<SbillitOrder> orderList = new ArrayList<SbillitOrder>();
		if (userSession.getSession().equals(session)){
			orderList = sbillitOrderDao.findOrderHistoryByUserId(userId);
		}
		// here we have to append shared orders
		List<SbillitOrderShare> tempOrderShareList = sbillitOrderDao.findOrderShareByUserIdAndOrderId(userId, null);
		//List<SbillitOrder> tempOrderList = new ArrayList<SbillitOrder>();
		for (SbillitOrderShare os: tempOrderShareList) {
			orderList.add(sbillitOrderDao.findOrderbyId(os.getOrderId(), NA_ORDER));
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
		if (orderShareArry.isArray() && orderShareArry.size()>0){
			order.setStatus(Constant.ORDER_SHARED);
		}else{
			order.setStatus(Constant.ORDER_CREATED);
		}
		order.setExpiredAt(expiredAt);
		sbillitOrderDao.createOrder(order);
		
		SbillitUser owner = sbillitUserDao.findUserById(ownerId);
		String nickName = owner.getNickname();
		owner.setPoint( owner.getPoint() + POINT_ORDER );
		sbillitUserDao.saveUser(owner);
		
		long orderId = order.getId();
		if (orderShareArry != null && orderShareArry.isArray()){
			StringBuffer inUserIds = new StringBuffer();
			
			for (JsonNode os: orderShareArry) {
				Long userId = os.get("userID").asLong();
				String phone = StringUtil.phoneNormalize(os.get("phoneNumber").asText());
				sbillitOrderDao.createOrderShare(orderId, phone, userId, Constant.ORDER_SHARE_AGREE);
				
				// through the userId, retrieve the device token and use apns
				SbillitUser user = sbillitUserDao.findUserById(userId);
				String token = user.getDeviceToken();
				if (token != null) {
					Apns.sendPush(nickName + " shared an order with you.", token);
				}
				if (userId != ownerId){
					inUserIds.append(" "+userId+",");
				}
			}
			
			SbillitFeed feed = new SbillitFeed();
			feed.setInUserId(inUserIds.toString());
			feed.setOrderId(orderId);
			feed.setTitle(nickName + " shared an order with you.");
			feed.setType(Constant.FEED_ORDER_SHARE);
			feed.setExpiredAt(DateUtil.getExpiredTimeFromNow("feed.endure"));
			List<SbillitFeed> feedsList = new ArrayList<SbillitFeed> ();
			feedsList.add(feed);
			sbillitFeedDao.insertUserFeeds(feedsList);
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
		if (orderShareArray.isArray() && orderShareArray.size()>0){
			order.setStatus(Constant.ORDER_SHARED);
		}else{
			order.setStatus(Constant.ORDER_CREATED);
		}
		order.setExpiredAt(expiredAt);
		sbillitOrderDao.createOrder(order);
		
		long orderId = order.getId();
		
		SbillitUser owner = sbillitUserDao.findUserById(ownerId);
		String nickName = owner.getNickname();
		owner.setPoint( owner.getPoint() + POINT_ORDER );
		sbillitUserDao.saveUser(owner);
		
		// create order share records		
 		// duplication check point make sure the fa and ca dont share same phonenumber
		if (orderShareArray.isArray()){
			StringBuffer inUserIds = new StringBuffer();
			
			for (JsonNode os: orderShareArray) {
				String phone = StringUtil.phoneNormalize( os.get("phoneNumber").asText() );
				Long userId = os.get("userID").asLong();
				sbillitOrderDao.createOrderShare(orderId, phone, userId, Constant.ORDER_SHARE_NA);
				
				// through the userId, retrieve the device token and use apns
				SbillitUser user = sbillitUserDao.findUserById(userId);
				String token = user.getDeviceToken();
				if (token != null) {
					Apns.sendPush(nickName + " shared an order with you.", token);
				}
				if (userId != ownerId){
					inUserIds.append(" "+userId+",");
				}
			}
			
			SbillitFeed feed = new SbillitFeed();
			feed.setInUserId(inUserIds.toString());
			feed.setOrderId(orderId);
			feed.setTitle(nickName + " shared an order with you.");
			feed.setType(Constant.FEED_ORDER_SHARE);
			feed.setExpiredAt(DateUtil.getExpiredTimeFromNow("feed.endure"));
			List<SbillitFeed> feedsList = new ArrayList<SbillitFeed> ();
			feedsList.add(feed);
			sbillitFeedDao.insertUserFeeds(feedsList);
		}else {
			
		}

		return order;
	}

	@Override
	public Map<Long, String> uploadFile(long orderId, String filePath) {
		// TODO Auto-generated method stub
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId, VALID_ORDER);
		Map<Long, String> returnMap = new HashMap<Long, String>();
		if (order == null) {
			returnMap.put(Constant.ERROR_INTERNAL, Constant.ORDER_IMAGE_UPLOAD_FAILED_NO_ORDER);
		}else {
			returnMap.put(Constant.ERROR_FREE, Constant.ORDER_IMAGE_UPLOAD_SUCCESS);
			if (order.getPicture1() == null){
				sbillitOrderDao.updateOrder(orderId, null, null, null, filePath, null, null);
			}else if (order.getPicture2() == null){
				sbillitOrderDao.updateOrder(orderId, null, null, null, null,filePath, null);
			}else if (order.getPicture3() == null){
				sbillitOrderDao.updateOrder(orderId, null, null, null, null, null, filePath);
			}else {
				returnMap.put(Constant.ERROR_INTERNAL, Constant.ORDER_IMAGE_UPLOAD_FAILED_MAX);
			}
		}
		return returnMap;
	}

	@Override
	public long thumbup(Long orderId, Long userId, String title) {
		// TODO Auto-generated method stub
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId, VALID_ORDER);
		if (order == null) {
			return 0;
		}
		
		sbillitOrderDao.createOrderThumbup(orderId, userId, title);
		List<SbillitOrderItem> orderItemList = sbillitOrderDao.findOrderItemByOrderId(orderId);
		SbillitCombo orderCombo = new SbillitCombo();
		orderCombo.setId(orderId); // set order id here
		// for loop to build combo data
		// sbillitComboDao.createCombo(orderCombo);
		return orderId;
	}

	@Override
	public Map<String, Object> favoriteList(Long userId) {
		// TODO Auto-generated method stub
		List<SbillitOrderThumbup> orderThumbupList = sbillitOrderDao.findOrderThumbupByUserId(userId);
		List<Long> ids = new ArrayList<Long>();
		for (SbillitOrderThumbup orderThumbup: orderThumbupList) {
			ids.add(orderThumbup.getOrderId());
		}
		if (ids.size()>0) {
			List<SbillitOrder> orderList = sbillitOrderDao.findOrderInIds(ids);
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("orderThumbupList", orderThumbupList);
			returnMap.put("orderList", orderList);
			return returnMap;
		}
		return null;
	}

	@Override
	public Map<String, Object> orderDetail(Long userId, Long orderId) {
		// TODO Auto-generated method stub
		
		// here to add an exception for shared order
		
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId, NA_ORDER);
		List<SbillitOrderComment> orderCommentList = sbillitOrderDao.findOrderCommentByUserIdAndOrderId(null, orderId);
		List<SbillitOrderShare> orderShareList = sbillitOrderDao.findOrderShareByUserIdAndOrderId(null, orderId);
		List<SbillitOrderItem> orderItemList = sbillitOrderDao.findOrderItemByUserIdAndOrderId(null, orderId);
		List<SbillitOrderThumbup> orderThumbupList = sbillitOrderDao.findOrderThumbupByUserIdAndOrderId(null, orderId);
		
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
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId, VALID_ORDER);
		if (order==null) {
			return 0;
		}
		long commentId = sbillitOrderDao.createOrderComment(orderId, userId, atUserId, message, status);
		
		String nickName = sbillitUserDao.findUserById(userId).getNickname();
		Long targetUserId = order.getUserId();
		List<SbillitOrderShare> orderShareList = sbillitOrderDao.findOrderShareByUserIdAndOrderId(null, orderId);
		StringBuffer inUserIds = new StringBuffer();
		for (SbillitOrderShare so: orderShareList) {
			Long _userId = so.getUserId();
			if (userId != _userId) {
				inUserIds.append(" " + _userId + ",");
				String token = sbillitUserDao.findUserById(_userId).getDeviceToken();
				Apns.sendPush(nickName + " commented on the order.", token);
			}
		}
		if (userId != targetUserId){
			inUserIds.append(" "+targetUserId+",");
			String token = sbillitUserDao.findUserById(targetUserId).getDeviceToken();
			Apns.sendPush(nickName + " commented on the order.", token);
		}

		SbillitFeed feed = new SbillitFeed();
		feed.setInUserId(inUserIds.toString());
		feed.setOrderId(orderId);
		feed.setTitle(nickName + " commented on your order.");
		feed.setType(Constant.FEED_ORDER_COMMENT);
		feed.setExpiredAt(DateUtil.getExpiredTimeFromNow("feed.endure"));
		List<SbillitFeed> feedsList = new ArrayList<SbillitFeed> ();
		feedsList.add(feed);
		sbillitFeedDao.insertUserFeeds(feedsList);

		return commentId;
	}

	@Override
	public long modifyOrderItem(Long orderId, Long ownerId, Double totalAmount, JsonNode orderItemArray) {
		// TODO Auto-generated method stub
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId, VALID_ORDER);
		if (order==null) {
			return 0;
		}
		Long targetUserId = order.getUserId();
		if (targetUserId != ownerId) {
			List<SbillitOrderShare> orderShareList = sbillitOrderDao.findOrderShareByUserIdAndOrderId(ownerId, orderId);
			if (orderShareList.size() == 0) {
				return 0;
			}
		}
		
		//Double amountChange = 0d;
		String phone = "";
		if (orderItemArray != null && orderItemArray.isArray()){
			for (JsonNode oi: orderItemArray) {
				String itemName = oi.get("itemName").asText();
				JsonNode buyerArray = oi.get("buyerArray");
				Double itemPrice = oi.get("itemPrice").asDouble();
				Long itemTotalAmount = oi.get("itemTotalAmount").asLong();
				if (buyerArray != null && buyerArray.isArray()) {
					for (JsonNode buyer: buyerArray) {
						Long userId = buyer.get("buyer").get("userID").asLong();
						Long itemNum = buyer.get("buyAmount").asLong();
						phone =  StringUtil.phoneNormalize(buyer.get("buyer").get("phoneNumber").asText());
						List<SbillitOrderItem> itemList = sbillitOrderDao.findOrderItemByUserIdAndOrderIdAndItem(userId, orderId, itemName);
						if (itemList.size() == 0) {
							sbillitOrderDao.createOrderItem(orderId, userId, itemNum, itemPrice, itemName);
						}else {
							sbillitOrderDao.updateOrderItem(orderId, userId, itemName, itemPrice, itemNum);
						}
						// we need to add push notification here !!!!
						//amountChange += itemPrice*itemTotalAmount;
					}
				}
			}
			sbillitOrderDao.updateOrderShare(orderId, phone, Constant.ORDER_SHARE_AGREE);
		}
		
		sbillitOrderDao.updateOrder(orderId, ownerId, null, totalAmount, null, null, null);
		
		List<SbillitOrderShare> orderShareList = sbillitOrderDao.findOrderShareByUserIdAndOrderId(null, orderId);
		String nickName = sbillitUserDao.findUserById(ownerId).getNickname();	
		StringBuffer inUserIds = new StringBuffer();
		for (SbillitOrderShare so: orderShareList) {
			if (ownerId != so.getUserId()) {
				Long _userId = so.getUserId();
				inUserIds.append(" "+_userId+",");
				String token = sbillitUserDao.findUserById(_userId).getDeviceToken();
				Apns.sendPush(nickName + " responsed on the order.", token);
			}
		}
		if (ownerId != targetUserId){
			inUserIds.append(" "+targetUserId+",");
			String token = sbillitUserDao.findUserById(targetUserId).getDeviceToken();
			Apns.sendPush(nickName + " responsed on the order.", token);
		}
		
		String inUserIdStr = inUserIds.toString();
		SbillitFeed feed = new SbillitFeed();
		feed.setInUserId(inUserIdStr);
		feed.setOrderId(orderId);
		feed.setTitle( nickName + " responsed to the order.");
		feed.setType(Constant.FEED_ORDER_OWN);
		feed.setExpiredAt(DateUtil.getExpiredTimeFromNow("feed.endure"));
		List<SbillitFeed> feedsList = new ArrayList<SbillitFeed> ();
		feedsList.add(feed);
		sbillitFeedDao.insertUserFeeds(feedsList);
		
		return orderId;
	}

	@Override
	public long closeOrder(Long orderId, Long ownerId) {
		// TODO Auto-generated method stub
		SbillitOrder order = sbillitOrderDao.findOrderbyId(orderId, VALID_ORDER);
		if (order==null || order.getUserId() != ownerId) {
			return 0;
		}
		sbillitOrderDao.updateOrder(orderId, ownerId, Constant.ORDER_CLOSE, null, null, null, null);
		return orderId;
	}

}
