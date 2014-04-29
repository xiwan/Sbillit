package services;

import java.util.List;
import java.util.Map;

import entity.SbillitUser;

public interface SbillitUserService {
	
	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(long id);
	
	public Long findUserByPhone(String phone);
	
	public String createNewUserWithPassword(String phone, String nickname, Integer deviceType, String deviceToken);
	
	public Map userLogin(String phone, String password);
	
	public String createNewUserAndAssignSmsToken (String phone, String nickname, Integer deviceType, String deviceToken);
	
	public Map completeNewUserRegister(String phone, String smsToken);
	
	public Long updateUserName(Long userId, String name);
	
	public Long updateAvatar(Long userId, String tempFilePath,  String newFilePath);
}