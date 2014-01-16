package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mapper.SbillitOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitOrder;
import entity.SbillitOrderItem;
import entity.SbillitOrderShare;

public class SbillitOrderDao {
	
	@Autowired
	private SbillitOrderMapper sbillitOrderMapper;

	public List<SbillitOrder> findAllOrders() {
		// TODO Auto-generated method stub
		return sbillitOrderMapper.findAllOrders();
	}

	public SbillitOrder findOrderbyId(long id) {
		// TODO Auto-generated method stub
		return sbillitOrderMapper.findOrderbyId(id);
	}

	public List<SbillitOrder> findOrderHistoryByUserId(long userId) {
		// TODO Auto-generated method stub
		return sbillitOrderMapper.findOrderHistoryByUserId(userId);
	}

	public void createOrder(SbillitOrder order) {
		// TODO Auto-generated method stub
		this.sbillitOrderMapper.updateSeq();
		this.sbillitOrderMapper.insertOrder(order);
	}

	public void createOrderShare(Long orderId, String phone, Long userId, Integer status){
		// TODO Auto-generated method stub
		SbillitOrderShare orderShare = new SbillitOrderShare();
		orderShare.setOrderId(orderId);
		orderShare.setStatus(status);
		orderShare.setPhone(phone);
		orderShare.setUserId(userId);
		this.sbillitOrderMapper.insertOrderShare(orderShare);
	}

	public void updateOrderShare(Long orderId, String phone, Integer status){
		// TODO Auto-generated method stub
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("orderId", orderId);
		paraMap.put("phone", phone);
		paraMap.put("status", status);
		this.sbillitOrderMapper.updateOrderShare(paraMap);	
	}

	public void updateOrder(Long orderId, Integer status, String picture1, String picture2, String picture3) {
		// TODO Auto-generated method stub
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("orderId", orderId);
		paraMap.put("status", status);
		paraMap.put("picture1", picture1);
		paraMap.put("picture2", picture2);
		paraMap.put("picture3", picture3);
		this.sbillitOrderMapper.updateOrder(paraMap);	
	}
	
	public void createOrderItem(Long orderId, Long userId, Long itemNum, Double itemPrice, String itemName) {
		SbillitOrderItem orderItem = new SbillitOrderItem();
		orderItem.setOrderId(orderId);
		orderItem.setUserId(userId);
		orderItem.setItemNum(itemNum);
		orderItem.setItemPrice(itemPrice);
		orderItem.setItemName(itemName);
		this.sbillitOrderMapper.insertOrderItem(orderItem);	
	}

}
