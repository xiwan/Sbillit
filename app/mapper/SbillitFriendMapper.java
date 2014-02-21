package mapper;

import java.util.List;
import java.util.Map;

import entity.SbillitFriend;

public interface SbillitFriendMapper {
	
	public List<SbillitFriend> findFriends(Map paraMap);
	
	public void insertFriends(Map paraMap);

}
