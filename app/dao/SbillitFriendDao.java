package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mapper.SbillitFriendMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitFriend;

public class SbillitFriendDao {
	@Autowired
	private SbillitFriendMapper sbillitFriendMapper;
	
	public List<SbillitFriend> findFriends (Long userId, Long friendId) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("friendId", friendId);
		return sbillitFriendMapper.findFriends(paraMap);
	};
	
	public void insertFriends(List<SbillitFriend> friendsList) {
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("friendsList", friendsList);
		sbillitFriendMapper.insertFriends(paraMap);
	};
	
	public void updateFriend(Long userId, Long friendId, Long status){
		Map paraMap = new HashMap<String, Object>();
		paraMap.put("userId", userId);
		paraMap.put("friendId", friendId);
		paraMap.put("status", status);
		sbillitFriendMapper.updateFriend(paraMap);
	}

}
