package dao;

public class SbillitUser {
	private int id;
	private String password;
	private int point;
	private int banned;
	private int snsId;
	private int snsType;
	private String snsToken;
	private String nickname;
	private String country;
	private String city;
	private int lastLoginAt;
	private int createdAt;
	private int updatedAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public int getBanned() {
		return banned;
	}
	public void setBanned(int banned) {
		this.banned = banned;
	}
	public int getSnsId() {
		return snsId;
	}
	public void setSnsId(int snsId) {
		this.snsId = snsId;
	}
	public int getSnsType() {
		return snsType;
	}
	public void setSnsType(int snsType) {
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
	public int getLastLoginAt() {
		return lastLoginAt;
	}
	public void setLastLoginAt(int lastLoginAt) {
		this.lastLoginAt = lastLoginAt;
	}
	public int getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
	public int getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(int updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
