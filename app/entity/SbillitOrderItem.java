package entity;

import java.io.Serializable;

public class SbillitOrderItem implements Serializable {
	private int orderId;
	private int itemId;
	private int itemNum;
	private float itemPrice;
	private String itemName;
	private int createdAt;
	private int updatedAt;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
