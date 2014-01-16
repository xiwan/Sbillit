package dao;

import mapper.SbillitUserSessionMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUserSession;

public class SbillitUserSessionDao {
	
	@Autowired
	private SbillitUserSessionMapper userSessionMapper;

	public SbillitUserSession getUserSessionByUserId(long userId) {
		// TODO Auto-generated method stub
		return userSessionMapper.getUserSessionByUserId(userId);
	}

	public SbillitUserSession getUserSessionBySession(String session) {
		// TODO Auto-generated method stub
		return userSessionMapper.getUserSessionBySession(session);
	}

	public void createUserSession(long userId, String session, long expiredAt) {
		// TODO Auto-generated method stub
		SbillitUserSession userSession = new SbillitUserSession();
		userSession.setUserId(userId);
		userSession.setSession(session);
		userSession.setExpiredAt(expiredAt);
		userSessionMapper.insertUserSession(userSession);
	}

	public void updateUserSession(SbillitUserSession userSession) {
		// TODO Auto-generated method stub
		userSessionMapper.updateUserSession(userSession);
	}

}
