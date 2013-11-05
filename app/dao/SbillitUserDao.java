package dao;

import entity.SbillitUser;

import java.util.List;


public interface SbillitUserDao {

	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(int id);
	
}
