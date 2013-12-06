package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import dao.SbillitOrderDao;
import dao.SbillitUserAuthtokenDao;

import entity.SbillitOrder;
import entity.SbillitUser;
import entity.SbillitUserAuthtoken;

public class SbillitOrderServiceImpl implements SbillitOrderService {
	@Autowired
	private SbillitOrderDao sbillitOrderDao;
	@Autowired
	private SbillitUserAuthtokenDao sbillitUserAuthtokenDao;
	
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

	@Override
	@Transactional
	public List<SbillitOrder> findOrderHistory(long userId, String sessionId) {
		// TODO Auto-generated method stub
		SbillitUserAuthtoken user = sbillitUserAuthtokenDao.getUserAuthtokenByUserId(userId);
		if (user.getAuthtoken().equals(sessionId)){
			return sbillitOrderDao.findOrderHistoryByUserId(userId);
		}else {
			return null;
		}	
	}

}
