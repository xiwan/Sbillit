package services;

import java.io.FileNotFoundException;
import java.io.IOException;

import entity.SbillitUserSession;

public interface SbillitSessionService {
	
	public SbillitUserSession getUserSessionByUserId(long userId);
	
	public SbillitUserSession getUserSessionBySession(String session);
	
	public String sessionCheckAndHandle(String session) 
			throws NumberFormatException, FileNotFoundException, IOException;

	public String createSession(long userId);
	
}
