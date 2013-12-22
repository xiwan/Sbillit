package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mapper.SbillitOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitOrder;
import entity.SbillitOrderShare;

public class SbillitOrderDaoImpl implements SbillitOrderDao {
	
	@Autowired
	private SbillitOrderMapper sbillitOrderMapper;

	@Override
	public List<SbillitOrder> findAllOrders() {
		// TODO Auto-generated method stub
		return sbillitOrderMapper.findAllOrders();
	}

	@Override
	public SbillitOrder findOrderbyId(long id) {
		// TODO Auto-generated method stub
		return sbillitOrderMapper.findOrderbyId(id);
	}

	@Override
	public List<SbillitOrder> findOrderHistoryByUserId(long userId) {
		// TODO Auto-generated method stub
		return sbillitOrderMapper.findOrderHistoryByUserId(userId);
	}

	@Override
	public void createOrder(SbillitOrder order) {
		// TODO Auto-generated method stub
		this.sbillitOrderMapper.updateSeq();
		this.sbillitOrderMapper.insertOrder(order);
	}

	@Override
	public void createOrderShare(Long orderId, String phone, Long userId, Integer status){
		// TODO Auto-generated method stub
		SbillitOrderShare orderShare = new SbillitOrderShare();
		orderShare.setOrderId(orderId);
		orderShare.setStatus(status);
		orderShare.setPhone(phone);
		orderShare.setUserId(userId);
		this.sbillitOrderMapper.insertOrderShare(orderShare);
	}

	@Override
	public void updateOrderShare(Long orderId, String phone, Integer status){
		// TODO Auto-generated method stub
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("orderId", orderId);
		paraMap.put("phone", phone);
		paraMap.put("status", status);
		this.sbillitOrderMapper.updateOrderShare(paraMap);	
	}

	@Override
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

}
