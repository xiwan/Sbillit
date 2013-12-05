package entity;

import java.io.Serializable;

public class SbillitUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String password;
	private long point;
	private int banned;
	private long inviteId;
	private long snsId;
	private long snsType;
	private String snsToken;
	private String nickname;
	private String country;
	private String city;
	private long lastLoginAt;
	private long createdAt;
	private long updatedAt;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPoint() {
		return point;
	}
	public void setPoint(long point) {
		this.point = point;
	}
	public int getBanned() {
		return banned;
	}
	public void setBanned(int banned) {
		this.banned = banned;
	}
	public long getInviteId() {
		return inviteId;
	}
	public void setInviteId(long inviteId) {
		this.inviteId = inviteId;
	}
	public long getSnsId() {
		return snsId;
	}
	public void setSnsId(long snsId) {
		this.snsId = snsId;
	}
	public long getSnsType() {
		return snsType;
	}
	public void setSnsType(long snsType) {
		this.snsType = snsType;
	}
	public String getSnsToken() {
		return snsToken;
	}
	public void setSnsToken(String snsToken) {
		this.snsToken = snsToken;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	public long getLastLoginAt() {
		return lastLoginAt;
	}
	public void setLastLoginAt(long lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
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
	
	
}
