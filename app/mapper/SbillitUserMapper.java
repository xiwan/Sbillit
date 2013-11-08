package mapper;

import java.util.List;
import java.util.Map;

import entity.SbillitUser;

public interface SbillitUserMapper {
	
	public List<SbillitUser> findAllUsers();
	
	public SbillitUser findUserById(Map map);

}
