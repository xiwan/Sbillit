package dao;

import java.util.List;

import mapper.SbillitOrderMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitOrder;

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

}
