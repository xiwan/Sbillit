package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.SimpleResult;
import play.mvc.Http.Context;
import services.SbillitSessionService;


public class Intercept extends Action.Simple {
	
	@Autowired
	private SbillitSessionService sbillitSessionService;

	@Override
	public Promise<SimpleResult> call(Context ctx) throws Throwable {
		// TODO Auto-generated method stub
		// each request should come with a session id
		String sessionId = ctx.request().getQueryString("sessionId");
		if (sessionId == null){
			// need to login again
			ctx.session().put("sessionId", "");
		}else{
			// session check
			String parpareReturn = null;
			try {
				parpareReturn = sbillitSessionService.sessionCheckAndHandle(sessionId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ctx.session().put("sessionId", parpareReturn);
		}
        return delegate.call(ctx);
	}
}