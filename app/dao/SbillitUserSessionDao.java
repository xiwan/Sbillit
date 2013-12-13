package dao;

import entity.SbillitUserSession;

public interface SbillitUserSessionDao {
	
	public SbillitUserSession getUserSessionByUserId(long userId);
	
	public SbillitUserSession getUserSessionBySession(String session);
	
	public void createUserSession(long userId, String session, long expiredAt);
	
	public void updateUserSession(SbillitUserSession userSession);

}
