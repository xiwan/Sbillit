package dao;

import java.util.List;

import entity.SbillitOrder;

public interface SbillitOrderDao {
	
	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(int id);
	
	public List<SbillitOrder> findOrderHistoryByUserId(long userId);
	
	public void createOrder(SbillitOrder order);

}
