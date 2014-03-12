package common;

import java.util.Date;
import java.util.Map;


import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

public class Apsn {
	private static String apsnCertPath = AppProp.getPropertyValue("ios.apsn.cert.path");
	private static String apsnCertPass = AppProp.getPropertyValue("ios.apsn.cert.pass");
	
	public static  void senSandbox(String token, String message) {
		ApnsService service = APNS.newService().withCert(apsnCertPath, apsnCertPass).withProductionDestination().build();
		
		String payload = APNS.newPayload().alertBody(message).build();
		service.push(token, payload);

		Map<String, Date> inactiveDevices = service.getInactiveDevices();
		for (String deviceToken : inactiveDevices.keySet()) {
			System.out.println(deviceToken);
		}
		
	}
	
	public static  void senSandboxPush(String token, String message) {
		ApnsService service = APNS.newService().withCert(apsnCertPath, apsnCertPass).withSandboxDestination().build();
		
		String payload = APNS.newPayload().alertBody(message).build();
		service.push(token, payload);

		Map<String, Date> inactiveDevices = service.getInactiveDevices();
		for (String deviceToken : inactiveDevices.keySet()) {
			System.out.println(deviceToken);
		}
		
	}

}
