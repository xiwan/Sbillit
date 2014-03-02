package services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

public interface SbillitCloopenSmsService {
	
	public void sendSmsToUser(String phone, String smsToken) throws IOException;

}
