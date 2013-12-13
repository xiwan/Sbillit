package dao;

import java.util.List;

import mapper.SbillitUserMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUser;

public class SbillitUserDaoImpl implements SbillitUserDao {
	@Autowired
	private SbillitUserMapper userMapper;

	@Override
	public List<SbillitUser> findAllUsers() {
		// TODO Auto-generated method stub		
		return this.userMapper.findAllUsers();
	}

	@Override
	public SbillitUser findUserById(long id) {
		// TODO Auto-generated method stub
		return this.userMapper.findUserById(id);
	}

	@Override
	public void insertUser(SbillitUser user) {
		// TODO Auto-generated method stub
		this.userMapper.updateSeq();
		this.userMapper.insertUser(user);
	}

	@Override
	public List<SbillitUser> findeUserByPhone(long phone) {
		// TODO Auto-generated method stub
		return userMapper.findUserByPhone(phone);
	}

	@Override
	public void saveUser (SbillitUser user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}


}
