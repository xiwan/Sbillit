package entity;

import java.io.Serializable;

public class SbillitBill implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private String paymentDetail;
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
	public String getPaymentDetail() {
		return paymentDetail;
	}
	public void setPaymentDetail(String paymentDetail) {
		this.paymentDetail = paymentDetail;
	}
	public int getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
	
	

}
