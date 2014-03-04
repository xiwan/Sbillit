package services;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.ning.http.util.Base64;

import utils.AppProp;
import utils.DateUtil;
import utils.JsonUtil;
import utils.Md5Util;

public class SbillitCloopenSmsServiceImpl implements SbillitCloopenSmsService {
	
	private static String httpsURL = AppProp.getPropertyValue("cloopen.rest.url");
	private static String softVersion = AppProp.getPropertyValue("cloopen.soft.version");
	private static String accountSid = AppProp.getPropertyValue("cloopen.account.sid");
	private static String subAccountSid = AppProp.getPropertyValue("cloopen.subaccount.sid");
	private static String appid = AppProp.getPropertyValue("cloopen.appid");
	private static String authToken = AppProp.getPropertyValue("cloopen.auth.token");
	private static String friendlyName = AppProp.getPropertyValue("cloopen.friendlyName");
	
	public class SMSQuery {
		public String to;
		public String body;
		public String msgType;
		public String appId;
		public String subAccountSid;
		public SMSQuery(String to, String body, String msgType, String appId, String subAccountSid){
			this.to = to;
			this.body = body;
			this.msgType = msgType;
			this.appId = appId;
			this.subAccountSid = subAccountSid;
		}
	};
	
	public class SubQuery {
		public String appId;
		public String friendlyName;
		public String subAccountSid;
		public SubQuery(String appId, String friendlyName, String subAccountSid){
			this.appId = appId;
			this.friendlyName = friendlyName;
			this.subAccountSid = subAccountSid;
		}
	}

//	static {
//	    //for localhost testing only
//	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
//	    new javax.net.ssl.HostnameVerifier(){
//	        public boolean verify(String hostname,
//	                javax.net.ssl.SSLSession sslSession) {
//	            if (hostname.equals("sandboxapp.cloopen.com")) {
//	                return true;
//	            }
//	            return false;
//	        }
//	    });
//	}
	
	private HttpsURLConnection byPassSSLSecurityCheck(String httpsRequest){
		// Imports: javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager
		try {
		    // Create a trust manager that does not validate certificate chains
		    final TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
		        @Override
		        public void checkClientTrusted( final X509Certificate[] chain, final String authType ) {}
		        @Override
		        public void checkServerTrusted( final X509Certificate[] chain, final String authType ) {}
		        @Override
		        public X509Certificate[] getAcceptedIssuers() {
		            return null;
		        }
		    } };
		    // Install the all-trusting trust manager
		    final SSLContext sslContext = SSLContext.getInstance( "SSL" );
		    sslContext.init( null, trustAllCerts, new java.security.SecureRandom() );
		    // Create an ssl socket factory with our all-trusting manager
		    final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		    URL myurl = new URL(httpsRequest);
			HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
			con.setSSLSocketFactory(sslSocketFactory);
		    return con;
		} catch ( final Exception e ) {
		    e.printStackTrace();
		}
		return null;
	}
	
	private String sendWrapper(String httpsRequest, String authorization, String query) throws IOException {
		HttpsURLConnection con = byPassSSLSecurityCheck(httpsRequest);
		if (con == null) {
			return null;
		}
		System.out.println(query);
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

		InputStreamReader input = new InputStreamReader(con.getInputStream());
		BufferedReader br = new BufferedReader(input);
		String returnLine = "";
		while ((returnLine = br.readLine()) != null){
			System.out.println(returnLine);
		}
		input.close(); 
		System.out.println("Resp Code:" + con.getResponseCode()); 
		System.out.println("Resp Message:" + con.getResponseMessage());	
		return returnLine;
	}
	
	@Override
	public String sendSmsToUser(String phone, String smsToken) throws IOException {
		// TODO Auto-generated method stub
		SMSQuery smsQuery = new SMSQuery(phone, smsToken, "0", appid, subAccountSid);
		String query = JsonUtil.toJson(smsQuery).toString();
		String timestamp = DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN2);
		String sig = Md5Util.MD5Encode(accountSid+authToken+timestamp).toUpperCase();
		String auth = accountSid + ":" + timestamp;
		String authorization = Base64.encode(auth.getBytes());				
		String httpsRequest = httpsURL + "/" + softVersion + "/Accounts/" + accountSid + "/SMS/Messages?sig=" + sig;
		
		return this.sendWrapper(httpsRequest, authorization, query);
	}

	@Override
	public String createSubaccount() throws IOException{
		// TODO Auto-generated method stub
		SubQuery subQuery = new SubQuery(appid, friendlyName, accountSid);
		String query = JsonUtil.toJson(subQuery).toString();
		String timestamp = DateUtil.format(new Date(), DateUtil.DATE_TIME_PATTERN2);
		String sig = Md5Util.MD5Encode(accountSid+authToken+timestamp).toUpperCase();
		String auth = accountSid + ":" + timestamp;
		String authorization = Base64.encode(auth.getBytes());
		String httpsRequest = httpsURL + "/" + softVersion + "/Accounts/" + accountSid + "/SubAccounts?sig=" + sig;
		
		return this.sendWrapper(httpsRequest, authorization, query);
	}

}
