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
	public SbillitOrder findOrderbyId(int id) {
		// TODO Auto-generated method stub
		return sbillitOrderMapper.findOrderbyId(id);
	}

}
