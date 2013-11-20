package entity;

import java.io.Serializable;

public class SbillitAccountFlow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int fromUserId;
	private int toUserId;
	private float amount;
	private int created_at;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFromUserId() {
		return fromUserId;
	}
	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}
	public int getToUserId() {
		return toUserId;
	}
	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}

}
