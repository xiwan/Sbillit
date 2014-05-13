package common;

import java.util.Date;
import java.util.Map;


import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.security.KeyStore;
import java.util.regex.Pattern;
 
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class Apns {
	
	public static  void sendProductionPush(String token, String message) {
		String apsnCertPath = AppProp.getPropertyValue("ios.apns.cert.path");
		String apsnCertPass = AppProp.getPropertyValue("ios.apns.cert.pass");
		ApnsService service = APNS.newService().withCert(apsnCertPath, apsnCertPass).withProductionDestination().build();
		
		String payload = APNS.newPayload().alertBody(message).build();
		service.push(token, payload);

		Map<String, Date> inactiveDevices = service.getInactiveDevices();
		for (String deviceToken : inactiveDevices.keySet()) {
			System.out.println(deviceToken);
		}
		
	}
	
	public void sendSandboxPush(String token, String message) {
		String apsnCertPath = AppProp.getPropertyValue("ios.apns.cert.path");
		String apsnCertPass = AppProp.getPropertyValue("ios.apns.cert.pass");
		System.out.println(apsnCertPath + " | " + apsnCertPass);
		ApnsService service = APNS.newService().withCert(apsnCertPath, apsnCertPass).withSandboxDestination().build();
		
		String payload = APNS.newPayload().alertBody(message).build();
		service.push(token, payload);
		Map<String, Date> inactiveDevices = service.getInactiveDevices();
		for (String deviceToken : inactiveDevices.keySet()) {
			System.out.println(deviceToken);
		}
	}
	
	public static void sendPush(String text, String deviceToken, String orderID) {
		String keyPath = AppProp.getPropertyValue("ios.apns.cert.path");
		String ksPassword = AppProp.getPropertyValue("ios.apns.cert.pass");
		String serverHost = AppProp.getPropertyValue("ios.apns.server"); 
        String ksType = "PKCS12";
        String ksAlgorithm = "SunX509";
        int serverPort = 2195;
        
        try{
        	InputStream certInput = new FileInputStream(keyPath);
        	KeyStore keyStore = KeyStore.getInstance(ksType);
        	keyStore.load(certInput, ksPassword.toCharArray());
        	
        	KeyManagerFactory kmf = KeyManagerFactory.getInstance(ksAlgorithm);
            kmf.init(keyStore, ksPassword.toCharArray());
            
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), null, null);
            
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            
            Socket socket = socketFactory.createSocket(serverHost, serverPort);
            
            StringBuilder content = new StringBuilder();

            content.append("{\"aps\":");
            content.append("{\"orderID\":\"").append(orderID).append("\",\"alert\":\"").append(text)
                .append("\",\"badge\":1,\"sound\":\"")
                .append("ping1").append("\"}");
            content.append(",\"cpn\":{\"t0\":")
            	.append(System.currentTimeMillis()).append("}");
            content.append("}");
            
            byte[] msgByte = makebyte((byte)1, deviceToken, content.toString(), 10000001);
            
            System.out.println(msgByte);
            
            socket.getOutputStream().write(msgByte);
            socket.getOutputStream().flush();
            
            socket.close();
        	
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
		
	}
	
	private static byte[] makebyte(byte command, String deviceToken, String payload, int identifer) {
		byte[] deviceTokenb = decodeHex(deviceToken);
        byte[] payloadBytes = null;
        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(boas);
 
        try {
            payloadBytes = payload.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
         
        try {
            dos.writeByte(command);
            dos.writeInt(identifer);//identifer
            dos.writeInt(Integer.MAX_VALUE);
            dos.writeShort(deviceTokenb.length);
            dos.write(deviceTokenb);
            dos.writeShort(payloadBytes.length);
            dos.write(payloadBytes);
            return boas.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
	}
	
	private static final Pattern pattern = Pattern.compile("[ -]");
	private static byte[] decodeHex(String deviceToken) {
		String hex = pattern.matcher(deviceToken).replaceAll("");
		byte[] bts = new byte[hex.length() / 2];
		for (int i=0; i<bts.length; i++) {
			bts[i] = (byte)(charval(hex.charAt(2*i)) * 16 + charval(hex.charAt(2*i + 1)));
		}
		return bts;
	}
	
	private static int charval(char a) {
		if ('0' <= a && a <= '9')
            return (a - '0');
        else if ('a' <= a && a <= 'f')
            return (a - 'a') + 10;
        else if ('A' <= a && a <= 'F')
            return (a - 'A') + 10;
        else{
            throw new RuntimeException("Invalid hex character: " + a);
        }
	}
	
	public static void main(String[] args) {
		System.out.println("xxxx");
		//ApnsService service = APNS.newService().withCert("D:\\develop\\git\\Sbillit\\Certificates_SBDev.p12", "1111").withSandboxDestination().build();
		
		//String payload = APNS.newPayload().alertBody("still test").build();
		//service.push("f898e34d a58fa342 9220905a 92313da8 62a4e5ad bbbcd3dc 6849d62a 718b81d2", payload);
		
		//Apns apsn = new Apns();
		//apsn.sendSandboxApns("小明小明", "D:\\develop\\git\\Sbillit\\Certificates_SBDev.p12", "1111", "f898e34d a58fa342 9220905a 92313da8 62a4e5ad bbbcd3dc 6849d62a 718b81d2");
		
	}

}
