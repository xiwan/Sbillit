package dao;

import entity.SbillitUserAuthtoken;

public interface SbillitUserAuthtokenDao {
	
	public SbillitUserAuthtoken getUserAuthtokenByUserId(long userId);
	
	public SbillitUserAuthtoken getUserAuthtokenBySessionId(String sessionId);
	
	public void createUserAuthtoken(long userId, String authtoken, long expiredAt);
	
	public void updateUserAuthtoken(SbillitUserAuthtoken userAuthtoken);

}
