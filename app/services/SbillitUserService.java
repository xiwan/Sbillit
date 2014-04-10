package services;

import java.util.List;
import java.util.Map;

import entity.SbillitUser;

public interface SbillitUserService {
	
	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(long id);
	
	public Long findUserByPhone(String phone);
	
	public String createNewUserAndAssignSmsToken (String phone, String nickname, Integer deviceType, String deviceToken);
	
	public Map completeNewUserRegister(String phone, String smsToken);
	
	public Long updateUserName(Long userId, String name);
	

}
