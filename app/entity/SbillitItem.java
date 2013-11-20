package entity;

import java.io.Serializable;

public class SbillitItem implements Serializable {
	private int id;
	private int sellerId;
	private float price;
	private float discountPrice;
	private String name;
	private int createdAt;
	private int udpatedAt;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
	public int getUdpatedAt() {
		return udpatedAt;
	}
	public void setUdpatedAt(int udpatedAt) {
		this.udpatedAt = udpatedAt;
	}	
	
}
