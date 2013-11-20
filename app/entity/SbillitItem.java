package entity;

import java.io.Serializable;

public class SbillitItem implements Serializable {
	private int id;
	private int sellerId;
	private double price;
	private double discountPrice;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
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
