package entity;

import java.io.Serializable;

public class SbillitFeed implements Serializable  {
	
	public Long id;
	public Long orderId;
	public String title;
	public Integer type;
	public Long createdAt;
	public String inUserId;
	public Long expiredAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public String getInUserId() {
		return inUserId;
	}
	public void setInUserId(String inUserId) {
		this.inUserId = inUserId;
	}
	public Long getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(Long expiredAt) {
		this.expiredAt = expiredAt;
	}
	
	

}
