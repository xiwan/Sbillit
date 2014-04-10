package services.impl;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import common.Constant;

import services.SbillitSessionService;
import utils.DateUtil;
import utils.Md5Util;

import dao.SbillitUserSessionDao;
import entity.SbillitUserSession;

@Transactional
public class SbillitSessionServiceImpl implements SbillitSessionService {
	
	private static String salt = "MONKEY_TYPE!@#$%";
	
	@Autowired
	private SbillitUserSessionDao userSessionDao;
	
	@Override
	public SbillitUserSession getUserSessionByUserId(long userId){
		// TODO Auto-generated method stub
		return userSessionDao.getUserSessionByUserId(userId);
	}
	
	@Override
	public SbillitUserSession getUserSessionBySession(String session) {
		// TODO Auto-generated method stub
		return userSessionDao.getUserSessionBySession(session);
	}
	
	@Override
	public String createSession(long userId) {
		// TODO Auto-generated method stub
		SbillitUserSession userSession = userSessionDao.getUserSessionByUserId(userId);
		String session = null;
		
		if (userSession == null){
			long currentTimestamp = DateUtil.GetCurrentTimeStamp();
			long expiredAt = DateUtil.getExpiredTimeFromNow("session.endure");
			
			Long currentTimestampLong = new Long(currentTimestamp);
			session = Md5Util.MD5Encode( currentTimestampLong.toString() + salt );
			userSessionDao.createUserSession(userId, session, expiredAt);
		}else {
			long expiredAt = DateUtil.getExpiredTimeFromNow("session.endure");
			userSession.setExpiredAt(expiredAt);
			userSessionDao.updateUserSession(userSession);
			session = userSession.getSession();
		}
		return session;
	}

	@Override
	@Transactional
	public String sessionCheckAndHandle(String session) throws NumberFormatException, FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		String sessionStatus = checkSessionStatus(session);
		String returnStr = null;
		if (sessionStatus == Constant.SESSION_NORMAL){
			long expiredAt = DateUtil.getExpiredTimeFromNow("session.endure");
			
			SbillitUserSession userSession = userSessionDao.getUserSessionBySession(session);
			userSession.setExpiredAt(expiredAt);
			userSessionDao.updateUserSession(userSession);
			returnStr = userSession.getSession();
		}else {
			returnStr = sessionStatus;
		}
		return returnStr;
	}

	private String checkSessionStatus(String session) {
		// TODO Auto-generated method stub
		SbillitUserSession userSession = userSessionDao.getUserSessionBySession(session);
		String returnStr = null;
		if (userSession == null) {
			returnStr = Constant.SESSION_NOT_EXIST;	
		}else {
			long expiredAt = userSession.getExpiredAt();
			if (expiredAt < DateUtil.GetCurrentTimeStamp()) {
				returnStr = Constant.SESSION_EXPIRED;
			}else {
				returnStr = Constant.SESSION_NORMAL;
			}
		}
		return returnStr;
	}


}
