package dao;

import java.util.List;

import mapper.SbillitUserMapper;

import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUser;

public class SbillitUserDaoImpl implements SbillitUserDao {
	@Autowired
	private SbillitUserMapper sbillitUserMapper;

	@Override
	public List<SbillitUser> findAllUsers() {
		// TODO Auto-generated method stub		
		return this.sbillitUserMapper.findAllUsers();
	}

	@Override
	public SbillitUser findUserById(long id) {
		// TODO Auto-generated method stub
		return this.sbillitUserMapper.findUserById(id);
	}

	@Override
	public void insertUser(SbillitUser user) {
		// TODO Auto-generated method stub
		this.sbillitUserMapper.updateSeq();
		this.sbillitUserMapper.insertUser(user);
	}

}
