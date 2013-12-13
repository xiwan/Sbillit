package mapper;

import entity.SbillitUserSession;

public interface SbillitUserSessionMapper {
	
	public SbillitUserSession getUserSessionByUserId(long userId);
	
	public SbillitUserSession getUserSessionBySession(String session);
	
	public void insertUserSession(SbillitUserSession userAuthtoken);
	
	public void updateUserSession(SbillitUserSession userSession);

}
