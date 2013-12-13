package services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import play.Logger;

import utils.Constant;
import utils.DateUtil;
import utils.Md5Util;
import utils.RamNumUtil;

import dao.SbillitUserDao;
import dao.SbillitUserSessionDao;
import entity.SbillitUser;

public class SbillitUserServiceImpl implements SbillitUserService {
	@Autowired
	private SbillitUserDao UserDao;
	
	@Autowired
	private SbillitUserSessionDao userSessionDao;

	@Override
	public List<SbillitUser> findAllUsers() {
		// TODO Auto-generated method stub
		return this.UserDao.findAllUsers();
	}

	@Override
	public SbillitUser findUserById(long id) {
		// TODO Auto-generated method stub
		return this.UserDao.findUserById(id);
	}

	@Override
	public String createNewUserAndAssignSmsToken(long phone, String nickname) {
		// TODO Auto-generated method stub
		long smsExpiredAt = DateUtil.getExpiredTimeFromNow("sms.endure");
		String smsToken = RamNumUtil.Instance().getRandom();
		
		SbillitUser user = new SbillitUser();
		user.setPhone(phone);
		user.setSmsToken(smsToken);
		user.setSmsExpiredAt(smsExpiredAt);
		user.setNickname(nickname);
		this.UserDao.insertUser(user);
		return smsToken;
	}

	@Override
	public Map completeNewUserRegister(long phone, String smsToken) {
		// TODO Auto-generated method stub
		List<SbillitUser> userList = this.UserDao.findeUserByPhone(phone);
		SbillitUser user = new SbillitUser();
		Map<String, SbillitUser> returnMap = new HashMap<String, SbillitUser>();
		if (userList == null) {
			returnMap.put(Constant.USER_PHONE_NOT_EXIST, user);
		}else {
			if (userList.size()>1){
				returnMap.put(Constant.USER_PHONE_DUPLICATE, user);
			}else{
				user = userList.get(0);
				Logger.debug(smsToken);
				if (user.getSmsToken().equals(smsToken)){
					long current = DateUtil.GetCurrentTimeStamp();
					long expired = user.getSmsExpiredAt();
					if (expired > current) {
						user.setSmsFlag(1l);
						this.UserDao.saveUser(user);
						returnMap.put(Constant.USER_NORMAL, user);
					}else {
						returnMap.put(Constant.USER_SMSTOKEN_EXPIRED, user);
					}
				}else {
					returnMap.put(Constant.USER_SMSTOKEN_NOT_MATCH, user);
				}
			}
		}	
		return returnMap;
	}

}
