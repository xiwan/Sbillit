

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import play.mvc.*;
import play.mvc.Http.Request;
import play.Application;
import play.GlobalSettings;
import services.SbillitSessionService;

public class Global extends GlobalSettings {
	
	private static ApplicationContext ctx;
	@Autowired
	private SbillitSessionService sbillitSessionService;
	
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
		
		return super.onRequest(request, actionMethod);
	}


}
