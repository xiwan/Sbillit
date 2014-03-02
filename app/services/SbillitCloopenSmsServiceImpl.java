package services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import org.eclipse.jetty.util.ajax.JSON;
import org.specs2.json.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.ning.http.util.Base64;

import utils.AppProp;
import utils.DateUtil;
import utils.JsonUtil;
import utils.Md5Util;

public class SbillitCloopenSmsServiceImpl implements SbillitCloopenSmsService {
	
	private static String httpsURL = AppProp.getPropertyValue("cloopen.rest.url");
	private static String softVersion = AppProp.getPropertyValue("cloopen.soft.version");
	private static String accountSid = AppProp.getPropertyValue("cloopen.account.sid");
	private static String subAccountSid = accountSid;
	private static String appid = AppProp.getPropertyValue("cloopen.appid");
	private static String authToken = AppProp.getPropertyValue("cloopen.auth.token");
	
	private class QueryLoad {
		String to;
		String body;
		String msgType;
		String appId;
		String subAccountSid;
	};
	
	@Override
	public void sendSmsToUser(String phone, String smsToken) throws IOException {
		// TODO Auto-generated method stub
		String timestamp = DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN2);
		String sig = Md5Util.MD5Encode(accountSid+authToken+timestamp).toUpperCase();
		String auth = accountSid+";"+timestamp;
		String authorization = Base64.encode(auth.getBytes());
				
		String httpsRequest = httpsURL + "/" + softVersion + "/Accounts/" + accountSid +
				"/SMS/Messages?sig=" + sig;
		
		QueryLoad queryLoad = new QueryLoad();
		queryLoad.to = phone;
		queryLoad.body = smsToken;
		queryLoad.msgType = "0";
		queryLoad.appId = appid;
		queryLoad.subAccountSid = subAccountSid;
		String query = JsonUtil.toJson(queryLoad).asText();
		
		URL myurl = new URL(httpsRequest);
		HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-length", String.valueOf(query.length())); 
		con.setRequestProperty("Content-Type","application/json;charset=utf-8"); 
		con.setRequestProperty("Accept", "application/json"); 
		con.setRequestProperty("Authorization", authorization);
		con.setDoOutput(true); 
		con.setDoInput(true);
		
		DataOutputStream output = new DataOutputStream(con.getOutputStream()); 
		output.writeBytes(query);
		output.close();
		
		DataInputStream input = new DataInputStream( con.getInputStream() ); 
		for( int c = input.read(); c != -1; c = input.read() ){
			System.out.print( (char)c ); 
		} 
		input.close(); 
		System.out.println("Resp Code:"+con .getResponseCode()); 
		System.out.println("Resp Message:"+ con .getResponseMessage()); 
		
	}

}
