package services;

import java.util.List;
import java.util.Map;

import entity.SbillitUser;

public interface SbillitUserService {
	
	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(long id);
	
	public Long findUserByPhone(String phone);
	
	public String createNewUserAndAssignSmsToken (String phone, String nickname);
	
	public Map completeNewUserRegister(String phone, String smsToken);
	

}
