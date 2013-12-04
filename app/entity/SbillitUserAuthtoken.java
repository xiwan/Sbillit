package entity;

import java.io.Serializable;

public class SbillitUserAuthtoken implements Serializable {
	
	public static long AUTHTOKEN_NOT_EXIST = 0l;
	public static long AUTHTOKEN_EXPIRED = 1l;
	public static long AUTHTOKEN_NORMAL = 2l;

	private long userId;
	private String authtoken;
	private long createdAt;
	private long updatedAt;
	private long expiredAt;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getAuthtoken() {
		return authtoken;
	}
	public void setAuthtoken(String authtoken) {
		this.authtoken = authtoken;
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
	public long getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(long expiredAt) {
		this.expiredAt = expiredAt;
	}
	
	
	
}
