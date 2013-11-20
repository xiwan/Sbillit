package entity;

import java.io.Serializable;

public class SbillitOrderCommen implements Serializable {
	private int  id;
	private int orderId;
	private int userId;
	private String message;
	private int status;
	private int atUserId;
	private int createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getAtUserId() {
		return atUserId;
	}
	public void setAtUserId(int atUserId) {
		this.atUserId = atUserId;
	}
	public int getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
	
}
