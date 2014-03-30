package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitCombo;
import entity.SbillitOrder;
import entity.SbillitOrderThumbup;

public interface SbillitOrderService {
	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(long id);
	
	public List<SbillitOrder> findOrderHistory(long userId, String session);
	
	public long createOrder(long ownerId, JsonNode orderShareArry, JsonNode orderItemArray, JsonNode orderCreator, 
			String orderImagePath, String orderComments, String orderTitle, Double amount);
	
	public SbillitOrder quickOrder(long ownerId, String orderTitle, JsonNode orderShareArray, Double amount);
	
	public Map<Long, String> uploadFile(long orderId, String filePath);
	
	public long thumbup(Long orderId, Long userId, String title);
	
	public Map<String, Object> favoriteList(Long userId);
	
	public Map<String, Object> orderDetail(Long userId, Long orderId);
	
	public long postComment(Long orderId, Long userId, Long atUserId, String message, Long status);
	
	public long modifyOrderItem(Long OrderId, Long ownerId, Double totalAmount, JsonNode orderItemArray);
	
	public long closeOrder(Long orderId, Long ownerId);
	
}
