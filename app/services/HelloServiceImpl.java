package services;

import org.springframework.beans.factory.annotation.Autowired;

import dao.SbillitUserDao;

@org.springframework.stereotype.Service
public class HelloServiceImpl implements HelloService {
//	@Autowired
//	private SbillitUserDao sbillitUserDao;
	
	@Override
	public String hello() {
		// TODO Auto-generated method stub
		return "Hello world!";
	}

}
