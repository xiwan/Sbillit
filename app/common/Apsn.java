package common;

import java.util.Date;
import java.util.Map;


import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class Apsn {
	static {
	    //for localhost testing only
	    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
	    new javax.net.ssl.HostnameVerifier(){
	        public boolean verify(String hostname,
	                javax.net.ssl.SSLSession sslSession) {
	            if (hostname.equals("gateway.sandbox.push.apple.com")) {
	                return true;
	            }
	            return false;
	        }
	    });
	}
	
	public static  void sendPush(String token, String message) {
		String apsnCertPath = AppProp.getPropertyValue("ios.apsn.cert.path");
		String apsnCertPass = AppProp.getPropertyValue("ios.apsn.cert.pass");
		ApnsService service = APNS.newService().withCert(apsnCertPath, apsnCertPass).withProductionDestination().build();
		
		String payload = APNS.newPayload().alertBody(message).build();
		service.push(token, payload);

		Map<String, Date> inactiveDevices = service.getInactiveDevices();
		for (String deviceToken : inactiveDevices.keySet()) {
			System.out.println(deviceToken);
		}
		
	}
	
	public static  void sendSandboxPush(String token, String message) {
		String apsnCertPath = AppProp.getPropertyValue("ios.apsn.cert.path");
		String apsnCertPass = AppProp.getPropertyValue("ios.apsn.cert.pass");
		
		ApnsService service = APNS.newService().withCert(apsnCertPath, apsnCertPass).withSandboxDestination().build();
		
		String payload = APNS.newPayload().alertBody(message).build();
		service.push(token, payload);
		Map<String, Date> inactiveDevices = service.getInactiveDevices();
		for (String deviceToken : inactiveDevices.keySet()) {
			System.out.println(deviceToken);
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("xxxx");
		ApnsService service = APNS.newService().withCert("/Users/xiwan/Documents/dev/workspace/Sbillit/SB_CertP.p12", "1111").withSandboxDestination().build();
		
		String payload = APNS.newPayload().alertBody("f898e34d a58fa342 9220905a 92313da8 62a4e5ad bbbcd3dc 6849d62a 718b81d2").build();
		service.push("f898e34d a58fa342 9220905a 92313da8 62a4e5ad bbbcd3dc 6849d62a 718b81d2", payload);
	}

}
