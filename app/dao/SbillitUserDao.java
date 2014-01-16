package dao;

import entity.SbillitUser;

import java.util.List;
import java.util.Map;

import mapper.SbillitUserMapper;

import org.springframework.beans.factory.annotation.Autowired;


public class SbillitUserDao {
	@Autowired
	private SbillitUserMapper userMapper;


	public List<SbillitUser> findAllUsers() {
		// TODO Auto-generated method stub		
		return this.userMapper.findAllUsers();
	}


	public SbillitUser findUserById(long id) {
		// TODO Auto-generated method stub
		return this.userMapper.findUserById(id);
	}


	public void insertUser(SbillitUser user) {
		// TODO Auto-generated method stub
		this.userMapper.updateSeq();
		this.userMapper.insertUser(user);
	}


	public List<SbillitUser> findeUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return userMapper.findUserByPhone(phone);
	}

	public void saveUser (SbillitUser user) {
		// TODO Auto-generated method stub
		userMapper.updateUser(user);
	}
}
