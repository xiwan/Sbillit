package services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dao.SbillitUserDao;
import entity.SbillitUser;

public class SbillitUserServiceImpl implements SbillitUserService {
	@Autowired
	private SbillitUserDao sbillitUserDao;

	@Override
	public List<SbillitUser> findAllUsers() {
		// TODO Auto-generated method stub
		return this.sbillitUserDao.findAllUsers();
	}

	@Override
	public SbillitUser findUserById(int id) {
		// TODO Auto-generated method stub
		return this.sbillitUserDao.findUserById(id);
	}

	@Override
	public long createUser(String password, long point, int banned,
			long inviteId, String snsToken, int snsType, String nickname) {
		// TODO Auto-generated method stub
		SbillitUser user = new SbillitUser();
		user.setPassword(password);
		user.setPoint(point);
		user.setBanned(banned);
		user.setInviteId(inviteId);
		user.setSnsToken(snsToken);
		user.setSnsType(snsType);
		user.setNickname(nickname);	
		this.sbillitUserDao.insertUser(user);
		return user.getId();
	}



}
