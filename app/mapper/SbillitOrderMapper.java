package mapper;

import java.util.List;

import entity.SbillitOrder;

public interface SbillitOrderMapper {

	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(int id);
	
	public List<SbillitOrder> findOrderHistoryByUserId(long userId);
	
	public void insertOrder(SbillitOrder order);
	
}
