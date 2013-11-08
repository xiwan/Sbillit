package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import utils.MyBatisFactory;

import dao.SbillitUserDao;
import entity.SbillitUser;

public class SbillitUserServiceImpl implements SbillitUserService {
	@Autowired
	private SbillitUserDao sbillitUserDao;

	@Override
	public List<SbillitUser> findAllUsers() {
		// TODO Auto-generated method stub
		return sbillitUserDao.findAllUsers();
	}


}
