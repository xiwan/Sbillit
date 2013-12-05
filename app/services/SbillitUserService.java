package services;

import java.util.List;

import entity.SbillitUser;

public interface SbillitUserService {
	
	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(int id);
	
	public long createUser(String password, long point, int banned,
			long inviteId, long snsId, int snsType, String nickname);

}
