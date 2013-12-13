package services;

import java.util.List;
import java.util.Map;

import entity.SbillitUser;

public interface SbillitUserService {
	
	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(long id);
	
	public String createNewUserAndAssignSmsToken (long phone, String nickname);
	
	public Map completeNewUserRegister(long phone, String smsToken);
	

}
