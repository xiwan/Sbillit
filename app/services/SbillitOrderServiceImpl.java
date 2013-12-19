package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import utils.AppProperties;
import utils.DateUtil;

import dao.SbillitOrderDao;
import dao.SbillitUserSessionDao;

import entity.SbillitOrder;
import entity.SbillitUser;
import entity.SbillitUserSession;

public class SbillitOrderServiceImpl implements SbillitOrderService {
	@Autowired
	private SbillitOrderDao sbillitOrderDao;
	@Autowired
	private SbillitUserSessionDao sbillitUserSessionDao;
	
	@Override
	public List<SbillitOrder> findAllOrders() {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findAllOrders();
	}

	@Override
	public SbillitOrder findOrderbyId(long id) {
		// TODO Auto-generated method stub
		return sbillitOrderDao.findOrderbyId(id);
	}

	@Override
	@Transactional
	public List<SbillitOrder> findOrderHistory(long userId, String session) {
		// TODO Auto-generated method stub
		SbillitUserSession userSession = sbillitUserSessionDao.getUserSessionByUserId(userId);
		List<SbillitOrder> orderList = new ArrayList<SbillitOrder>();
		if (userSession.getSession().equals(session)){
			orderList = sbillitOrderDao.findOrderHistoryByUserId(userId);
		}
		return orderList;
	}

	@Override
	public long createOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public SbillitOrder quickOrder(long userId, List<Long> userIdList, float amount) {
		// TODO Auto-generated method stub
		long expiredAt = DateUtil.getExpiredTimeFromNow("order.endure");
		
		SbillitOrder order = new SbillitOrder();
		order.setUserId(userId);
		order.setType(0l);
		order.setTitle("quick create");
		order.setAmount(amount);
		order.setStatus(1);
		order.setExpiredAt(expiredAt);
		sbillitOrderDao.createOrder(order);
		
		long orderId = order.getId();
		sbillitOrderDao.findOrderbyId(order.getId());
		
		for (Long uid: userIdList) {
			sbillitOrderDao.createOrderShare(orderId, uid, 0);
		}	
		
		return order;
	}

}
