package dao;

import entity.SbillitUser;

import java.util.List;
import java.util.Map;


public interface SbillitUserDao {

	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(long id);
	
	public List<SbillitUser> findeUserByPhone(String phone);
	
	public void insertUser(SbillitUser user);
	
	public void saveUser(SbillitUser user);
}
