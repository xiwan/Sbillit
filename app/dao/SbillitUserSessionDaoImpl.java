package dao;

import mapper.SbillitUserSessionMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUserSession;

public class SbillitUserSessionDaoImpl implements SbillitUserSessionDao {
	
	@Autowired
	private SbillitUserSessionMapper userSessionMapper;

	@Override
	public SbillitUserSession getUserSessionByUserId(long userId) {
		// TODO Auto-generated method stub
		return userSessionMapper.getUserSessionByUserId(userId);
	}

	@Override
	public SbillitUserSession getUserSessionBySession(String session) {
		// TODO Auto-generated method stub
		return userSessionMapper.getUserSessionBySession(session);
	}

	@Override
	public void createUserSession(long userId, String session, long expiredAt) {
		// TODO Auto-generated method stub
		SbillitUserSession userSession = new SbillitUserSession();
		userSession.setUserId(userId);
		userSession.setSession(session);
		userSession.setExpiredAt(expiredAt);
		userSessionMapper.insertUserSession(userSession);
	}

	@Override
	public void updateUserSession(SbillitUserSession userSession) {
		// TODO Auto-generated method stub
		userSessionMapper.updateUserSession(userSession);
	}



}
