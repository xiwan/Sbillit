package entity;

import java.io.Serializable;

public class SbillitInvite implements Serializable {
	private int id;
	private int userId;
	private int messageId;
	private String newMessage;
	private int createdAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getNewMessage() {
		return newMessage;
	}
	public void setNewMessage(String newMessage) {
		this.newMessage = newMessage;
	}
	public int getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}

}
