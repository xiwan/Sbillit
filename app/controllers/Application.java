package controllers;


import play.*;
import play.mvc.*;
import views.html.*;

import services.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;

import dao.SbillitUser;
import dao.SbillitUserDao;

public class Application extends Controller {

    public static Result index() {
    	SqlSession sqlSession = MyBatisFactory.getDefaultClient();
    	SbillitUserDao sbillitUserDao = sqlSession.getMapper(SbillitUserDao.class);
    	String nickname = sbillitUserDao.findAllUsers().get(0).getNickname();
    	//System.out.println();
    	sqlSession.close();
    	return ok("Your new application is ready." + nickname);
    }

}
