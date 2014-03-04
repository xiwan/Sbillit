package services;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

import entity.SbillitFriend;

public interface SbillitSnsService {
	
	public List<SbillitFriend> findFriends (Long userId, Long friendId);
	
	public long addFriends(Long userId, JsonNode friendArr, Long status);
	
	public void updateFriend(Long userId, Long friendId, Long status);

}
