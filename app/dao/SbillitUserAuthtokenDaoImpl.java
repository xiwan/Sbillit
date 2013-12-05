package dao;

import mapper.SbillitUserAuthtokenMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUserAuthtoken;

public class SbillitUserAuthtokenDaoImpl implements SbillitUserAuthtokenDao {
	
	@Autowired
	private SbillitUserAuthtokenMapper sbillitUserAuthtokenMapper;

	@Override
	public SbillitUserAuthtoken getUserAuthtokenByUserId(long userId) {
		// TODO Auto-generated method stub
		return sbillitUserAuthtokenMapper.getUserAuthtokenByUserId(userId);
	}
	
	@Override
	public SbillitUserAuthtoken getUserAuthtokenBySessionId(String sessionId){
		// TODO Auto-generated method stub
		return sbillitUserAuthtokenMapper.getUserAuthtokenBySessionId(sessionId);
	}

	@Override
	public void createUserAuthtoken(long userId, String authtoken, long expiredAt) {
		// TODO Auto-generated method stub
		SbillitUserAuthtoken  sbillitUserAuthtoken = new SbillitUserAuthtoken();
		sbillitUserAuthtoken.setUserId(userId);
		sbillitUserAuthtoken.setAuthtoken(authtoken);
		sbillitUserAuthtoken.setExpiredAt(expiredAt);
		sbillitUserAuthtokenMapper.insertUserAuthtoken(sbillitUserAuthtoken);
	}

	@Override
	public void updateUserAuthtoken(SbillitUserAuthtoken userAuthtoken) {
		// TODO Auto-generated method stub
		sbillitUserAuthtokenMapper.updateUserAuthtoken(userAuthtoken);
	}



}
