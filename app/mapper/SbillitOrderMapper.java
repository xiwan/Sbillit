package mapper;

import java.util.List;
import java.util.Map;

import entity.SbillitOrder;
import entity.SbillitOrderItem;
import entity.SbillitOrderShare;
import entity.SbillitOrderThumbup;

public interface SbillitOrderMapper {

	public List<SbillitOrder> findAllOrders();
	
	public SbillitOrder findOrderbyId(long id);
	
	public List<SbillitOrder> findOrderHistoryByUserId(long userId);
	
	public void insertOrder(SbillitOrder order);
	
	public void updateSeq();
	
	public void insertOrderShare(SbillitOrderShare orderShare);

	public void updateOrderShare(Map paraMap);
	
	public void updateOrder(Map paraMap);
	
	public void insertOrderItem(SbillitOrderItem orderItem);
	
	public void insertOrderThumbup(SbillitOrderThumbup orderThumbup);
	
	public List<SbillitOrder> findOrderInIds(Map paraMap);
	
	public List<SbillitOrderThumbup> findOrderThumbupByUserId(Long userId);
	
}
