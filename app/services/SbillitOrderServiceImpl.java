package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.SbillitOrderDao;

import entity.SbillitOrder;

public class SbillitOrderServiceImpl implements SbillitOrderService {
	@Autowired
	private SbillitOrderDao sbillitOrderDao;
	
	@Override
	public List<SbillitOrder> findAllOrders() {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findAllOrders();
	}

	@Override
	public SbillitOrder findOrderbyId(int id) {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findOrderbyId(id);
	}

}
