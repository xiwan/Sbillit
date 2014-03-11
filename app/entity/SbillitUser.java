package entity;

import java.io.Serializable;

public class SbillitUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// table columns
	private long id;
	private String phone;
	private long banned;
	private long point;
	private int deviceType;
	private String deviceToken;
	private String smsToken;
	private long smsFlag;
	private long smsExpiredAt;
	private long inviteId;
	private String nickname;
	private String password;
	private String country;
	private String city;
	private long createdAt;
	private long updatedAt;
	// pending
	private String session;
	
	public int getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(int deviceType) {
		this.deviceType = deviceType;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public long getBanned() {
		return banned;
	}
	public void setBanned(long banned) {
		this.banned = banned;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(long point) {
		this.point = point;
	}
	public String getSmsToken() {
		return smsToken;
	}
	public void setSmsToken(String smsToken) {
		this.smsToken = smsToken;
	}
	public long getSmsFlag() {
		return smsFlag;
	}
	public void setSmsFlag(long smsFlag) {
		this.smsFlag = smsFlag;
	}
	public long getSmsExpiredAt() {
		return smsExpiredAt;
	}
	public void setSmsExpiredAt(long smsExpiredAt) {
		this.smsExpiredAt = smsExpiredAt;
	}
	public long getInviteId() {
		return inviteId;
	}
	public void setInviteId(long inviteId) {
		this.inviteId = inviteId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	public long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	
	
	
	
}
