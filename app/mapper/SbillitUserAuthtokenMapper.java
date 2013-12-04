package mapper;

import entity.SbillitUserAuthtoken;

public interface SbillitUserAuthtokenMapper {
	
	public SbillitUserAuthtoken getUserAuthtokenByUserId(long userId);
	
	public void insertUserAuthtoken(SbillitUserAuthtoken sbillitUserAuthtoken);
	
	public void updateUserAuthtoken(SbillitUserAuthtoken sbillitUserAuthtoken);

}
