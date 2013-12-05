package services;

import java.io.FileNotFoundException;
import java.io.IOException;

import entity.SbillitUserAuthtoken;

public interface SbillitSessionService {
	
	public SbillitUserAuthtoken getUserAuthtokenByUserId(long userId);
	
	public String sessionCheckAndHandle(String sessionId) 
			throws NumberFormatException, FileNotFoundException, IOException;

	public String createSession(long userId) 
			throws NumberFormatException, FileNotFoundException, IOException;
	
}
