package services;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import utils.AppProperties;
import utils.DateUtil;
import utils.Md5Util;

import dao.SbillitUserAuthtokenDao;
import entity.SbillitUserAuthtoken;

public class SbillitSessionServiceImpl implements SbillitSessionService {
	
	private static String salt = "MONKEY_TYPE!@#$%";
	
	@Autowired
	private SbillitUserAuthtokenDao sbillitUserAuthtokenDao;
	
	@Override
	public SbillitUserAuthtoken getUserAuthtokenByUserId(long userId){
		return sbillitUserAuthtokenDao.getUserAuthtokenByUserId(userId);
	}

	@Override
	public String sessionCheckAndHandle(long userId) throws NumberFormatException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		long sessionStatus = checkSessionStatus(userId);
		if (sessionStatus == SbillitUserAuthtoken.AUTHTOKEN_EXPIRED) {
			// session expired, need to login again
			return "expried";
		}else if (sessionStatus == SbillitUserAuthtoken.AUTHTOKEN_NORMAL) {
			// normal, update session expire time
			AppProperties appProperties = new AppProperties();
			DateUtil dateUtil = new DateUtil();
			long currentTimestamp = dateUtil.GetCurrentTimeStamp();
			long expiredAt = currentTimestamp + Long.parseLong(appProperties.getPropertyValue("session.endure"));
			
			SbillitUserAuthtoken userAuthtoken = sbillitUserAuthtokenDao.getUserAuthtokenByUserId(userId);
			userAuthtoken.setExpiredAt(expiredAt);
			sbillitUserAuthtokenDao.updateUserAuthtoken(userAuthtoken);
			return userAuthtoken.getAuthtoken();
		}else if (sessionStatus == SbillitUserAuthtoken.AUTHTOKEN_NOT_EXIST) {
			// not exist, create a new session
			AppProperties appProperties = new AppProperties();
			DateUtil dateUtil = new DateUtil();
			long currentTimestamp = dateUtil.GetCurrentTimeStamp();
			long expiredAt = currentTimestamp + Long.parseLong(appProperties.getPropertyValue("session.endure"));
			
			Long currentTimestampLong = new Long(currentTimestamp);
			String authtoken = Md5Util.MD5Encode( currentTimestampLong.toString() + salt );
			sbillitUserAuthtokenDao.createUserAuthtoken(userId, authtoken, expiredAt);
			return authtoken;
		}
		return null;
	}

	
	private long checkSessionStatus(long userId) {
		// TODO Auto-generated method stub
		SbillitUserAuthtoken userAuthtoken = sbillitUserAuthtokenDao.getUserAuthtokenByUserId(userId);
		if (userAuthtoken != null) {
			DateUtil dateUtil = new DateUtil();
			long expiredAt = userAuthtoken.getExpiredAt();
			if (expiredAt < dateUtil.GetCurrentTimeStamp()) {
				return SbillitUserAuthtoken.AUTHTOKEN_EXPIRED;
			}
			return SbillitUserAuthtoken.AUTHTOKEN_NORMAL;
		}
		return SbillitUserAuthtoken.AUTHTOKEN_NOT_EXIST;	
	}

}
