package entity;

import java.io.Serializable;

public class SbillitFriend implements Serializable {
	private int userId;
	private int friendId;
	private int status;
	private int createdAt;
	private int updatedAt;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
