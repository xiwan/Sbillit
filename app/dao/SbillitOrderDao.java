package dao;

import java.util.List;

import entity.SbillitOrder;

public interface SbillitOrderDao {
	
	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(long id);
	
	public List<SbillitOrder> findOrderHistoryByUserId(long userId);
	
	public void createOrder(SbillitOrder order);
	
	public void createOrderShare(Long orderId, String phone, Long userId, Integer status);
	
	public void updateOrderShare(Long orderId, String phone, Integer status);

	public void updateOrder(Long orderId, Integer status, String picture1, String picture2, String picture3);

	
}
