package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.SbillitFriendDao;

import entity.SbillitFriend;

public class SbillitSnsServiceImpl implements SbillitSnsService {
	@Autowired
	private SbillitFriendDao sbillitFriendDao;

	@Override
	public List<SbillitFriend> findFriends(Long userId, Long friendId) {
		// TODO Auto-generated method stub
		return sbillitFriendDao.findFriends(userId, friendId);
	}

	@Override
	public long addFriends(Long userId, Long friendId, Long status) {
		// TODO Auto-generated method stub
		
		List<SbillitFriend> existingFriendList = sbillitFriendDao.findFriends(userId, friendId);
		if (existingFriendList.size() == 0) {
			
			List<SbillitFriend> friendsList = new ArrayList<SbillitFriend> ();
			SbillitFriend friend = new SbillitFriend();
			friend.setUserId(userId);
			friend.setFriendId(friendId);
			friend.setStatus(status);
			friendsList.add(friend);
			sbillitFriendDao.insertFriends(friendsList);
			
			return 0l;
		}else if (existingFriendList.size() == 1) {
			// SbillitFriend friend = existingFriendList.get(0);
			return 1l;
		}
		return -1l;
	}
	
	

}
