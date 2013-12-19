package services;

import java.util.ArrayList;
import java.util.List;

import entity.SbillitOrder;

public interface SbillitOrderService {
	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(long id);
	
	public List<SbillitOrder> findOrderHistory(long userId, String session);
	
	public long createOrder();
	
	public SbillitOrder quickOrder(long userId, List<Long> userIdList, float amount);
}
