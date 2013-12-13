package mapper;

import java.util.List;

import entity.SbillitUser;

public interface SbillitUserMapper {
	
	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(long id);
	
	public List<SbillitUser> findUserByPhone(long phone);
	
	public void updateUser(SbillitUser user);
	
	public void insertUser(SbillitUser user);
	
	public void updateSeq();

}
