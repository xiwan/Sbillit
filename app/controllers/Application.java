package controllers;


import play.*;
import play.mvc.*;
import services.HelloService;
import services.HelloServiceImpl;
import utils.MyBatisFactory;
import views.html.*;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import entity.SbillitUser;
import dao.SbillitUserDao;

@org.springframework.stereotype.Controller
public class Application extends Controller {
	
	@Autowired
	private HelloService helloService;

    public Result index() {
    	SqlSession sqlSession = MyBatisFactory.getDefaultClient();
    	SbillitUserDao sbillitUserDao = sqlSession.getMapper(SbillitUserDao.class);
    	String nickname = sbillitUserDao.findAllUsers().get(0).getNickname();
    	nickname = sbillitUserDao.findUserById(1).getNickname();
    	sqlSession.close();
    	//System.out.println(helloService.hello());
    	return ok("Your new application is ready." + nickname);
    }

}
