

import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.mvc.*;
import play.mvc.Http.Request;
import play.Application;
import play.GlobalSettings;

public class Global extends GlobalSettings {
	
	private static ApplicationContext ctx;
	
	@Override
	public void onStart(Application app){
		ctx = new ClassPathXmlApplicationContext("application-context.xml");
	}
	
	@Override
	public <A> A getControllerInstance(Class<A> klass){
		if (ctx == null) {
            throw new IllegalStateException("application context is not initialized");
        }
		return ctx.getBean(klass);
	}
	
	@Override
	public Action onRequest(Request request, Method actionMethod) {
	   System.out.println("before each request..." + request.toString());
	   return super.onRequest(request, actionMethod);
	}


}
