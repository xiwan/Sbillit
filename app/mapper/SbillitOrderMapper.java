package mapper;

import java.util.List;
import java.util.Map;

import entity.SbillitOrder;
import entity.SbillitOrderComment;
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
	public void updateOrderItem(Map paraMap);
	
	public void insertOrderThumbup(SbillitOrderThumbup orderThumbup);
	
	public List<SbillitOrder> findOrderInIds(Map paraMap);
	
	public List<SbillitOrderThumbup> findOrderThumbupByUserId(Long userId);
	
	public List<SbillitOrderItem> findOrderItemByOrderId(Long orderId);
	
	public void insertOrderComment(SbillitOrderComment orderComment);
	public void updateCommentSeq();
	
	public List<SbillitOrderShare> findOrderShareByUserIdAndOrderId (Map paraMap);
	public List<SbillitOrderItem> findOrderItemByUserIdAndOrderId (Map paraMap);
	public List<SbillitOrderItem> findOrderItemByUserIdAndOrderIdAndItem(Map paraMap);
	public List<SbillitOrderThumbup> findOrderThumbupByUserIdAndOrderId (Map paraMap);
	public List<SbillitOrderComment> findOrderCommentByUserIdAndOrderId (Map paraMap);
	
	
	
}
