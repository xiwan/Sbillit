package dao;

import entity.SbillitUser;

import java.util.List;
import java.util.Map;


public interface SbillitUserDao {

	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(Map map);
	
}
