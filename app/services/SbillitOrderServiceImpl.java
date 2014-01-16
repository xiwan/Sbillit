package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import utils.AppProp;
import utils.Constant;
import utils.DateUtil;

import dao.SbillitOrderDao;
import dao.SbillitUserSessionDao;

import entity.SbillitOrder;
import entity.SbillitUser;
import entity.SbillitUserSession;

public class SbillitOrderServiceImpl implements SbillitOrderService {
	@Autowired
	private SbillitOrderDao sbillitOrderDao;
	@Autowired
	private SbillitUserSessionDao sbillitUserSessionDao;
	
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
	public SbillitOrder quickOrder(long ownerId, String orderTitle, JsonNode friendsArray, JsonNode contactsArray, Double amount) {
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
		if (friendsArray.isArray()){
			for (JsonNode fa: friendsArray) {
				String phone = fa.get("phoneNumber").asText();
				Long userId = fa.get("userID").asLong();
				sbillitOrderDao.createOrderShare(orderId, phone, userId, Constant.ORDER_SHARE_NA);
			}			
		}else {
			
		}
		
		if (contactsArray.isArray()){
			for (JsonNode ca: contactsArray) {
				String phone = ca.get("phoneNumber").asText();
				sbillitOrderDao.createOrderShare(orderId, phone, 0l, Constant.ORDER_SHARE_NA);			
			}			
		}else  {
			
		}

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

}
