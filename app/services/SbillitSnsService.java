package services;

import java.util.List;

import entity.SbillitFriend;

public interface SbillitSnsService {
	
	public List<SbillitFriend> findFriends (Long userId, Long friendId);
	
	public long addFriends(Long userId, Long friendId, Long status);

}
