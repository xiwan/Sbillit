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
	public SbillitUser findUserById(int id) {
		// TODO Auto-generated method stub
		return this.sbillitUserMapper.findUserById(id);
	}

}
