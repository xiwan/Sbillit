package utils;

import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import play.Application;
import play.Configuration;
import play.Play;

public class MyBatisFactory {
	private static MyBatisFactory instance;
	private static String svrName = "default";

	{
		instance = new MyBatisFactory();
	}
	
	public synchronized static SqlSession getDefaultClient(){

		return newSqlSession();
	}
	
	public static SqlSession newSqlSession() {

		Properties props = new Properties();
		Configuration config = Play.application().configuration();

		String resource = config.getString("mybatis.configuration");

		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}

		props.setProperty("driver", config.getString("db.default.driver"));
		props.setProperty("url", config.getString("db.default.url"));
		props.setProperty("username", config.getString("db.default.user"));
		props.setProperty("password", config.getString("db.default.password"));

		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader, props);

		return sessionFactory.openSession();

	}

}
