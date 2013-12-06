package services;

import java.util.List;

import entity.SbillitOrder;

public interface SbillitOrderService {
	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(int id);
	
	public List<SbillitOrder> findOrderHistory(long userId, String sessionId);
}
