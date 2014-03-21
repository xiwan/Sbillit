package services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import services.SbillitSnsService;

import com.fasterxml.jackson.databind.JsonNode;

import dao.SbillitFriendDao;
import dao.SbillitUserDao;

import entity.SbillitFriend;
import entity.SbillitUser;

public class SbillitSnsServiceImpl implements SbillitSnsService {
	@Autowired
	private SbillitFriendDao sbillitFriendDao;
	@Autowired
	private SbillitUserDao UserDao;

	@Override
	public List<SbillitFriend> findFriends(Long userId, Long friendId) {
		// TODO Auto-generated method stub
		return sbillitFriendDao.findFriends(userId, friendId);
	}

	@Override
	public long addFriends(Long userId, JsonNode friendArr, Long status) {
		// TODO Auto-generated method stub
		long flag = 0;
		List<SbillitFriend> friendsList = new ArrayList<SbillitFriend> ();
		
		if (friendArr != null && friendArr.isArray()){
			for (JsonNode fr: friendArr) {
				String phone = fr.get("phoneNumber").asText();
				String name = fr.get("name").asText();
				List<SbillitUser> userList = this.UserDao.findeUserByPhone(phone);
				if (userList == null || userList.size() == 0) {
					SbillitUser user = new SbillitUser();
					user.setPhone(phone);
					user.setNickname(name);
					user.setSmsToken("");
					this.UserDao.insertUser(user);
					Long friendId = user.getId();
					
					SbillitFriend friend = new SbillitFriend();
					friend.setUserId(userId);
					friend.setFriendId(friendId);
					friend.setStatus(status);
					friendsList.add(friend);
				}else if (userList.size() == 1){
					Long friendId = userList.get(0).getId();
					List<SbillitFriend> existingFriendList = sbillitFriendDao.findFriends(userId, friendId);
					if (existingFriendList.size() == 0) {
						SbillitFriend friend = new SbillitFriend();
						friend.setUserId(userId);
						friend.setFriendId(friendId);
						friend.setStatus(status);
						friendsList.add(friend);
					}else {
						flag = 1;
						continue;
					}
				}else {
					flag = -1;
					break;
				}
			}
			if (friendsList.size()>0){
				sbillitFriendDao.insertFriends(friendsList);
			}
		}
		return flag;
	}

	@Override
	public void updateFriend(Long userId, Long friendId, Long status) {
		// TODO Auto-generated method stub
		sbillitFriendDao.updateFriend(userId, friendId, status);
	}

}
