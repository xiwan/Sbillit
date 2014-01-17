package dao;

import mapper.SbillitUserSessionMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUserSession;

public class SbillitUserSessionDao {
	
	@Autowired
	private SbillitUserSessionMapper userSessionMapper;

	public SbillitUserSession getUserSessionByUserId(long userId) {
		return userSessionMapper.getUserSessionByUserId(userId);
	}

	public SbillitUserSession getUserSessionBySession(String session) {
		return userSessionMapper.getUserSessionBySession(session);
	}

	public void createUserSession(long userId, String session, long expiredAt) {
		SbillitUserSession userSession = new SbillitUserSession();
		userSession.setUserId(userId);
		userSession.setSession(session);
		userSession.setExpiredAt(expiredAt);
		userSessionMapper.insertUserSession(userSession);
	}

	public void updateUserSession(SbillitUserSession userSession) {
		userSessionMapper.updateUserSession(userSession);
	}

}
