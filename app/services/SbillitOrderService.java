package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitOrder;

public interface SbillitOrderService {
	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(long id);
	
	public List<SbillitOrder> findOrderHistory(long userId, String session);
	
	public long createOrder();
	
	public SbillitOrder quickOrder(long ownerId, String orderTitle, JsonNode friendsArray, JsonNode contactsArray, Double amount);
	
	public Map<Long, String> uploadFile(long orderId, String filePath);
}
