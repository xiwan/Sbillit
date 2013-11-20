package entity;

import java.io.Serializable;

public class SbillitAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int userId;
	private float balance; 
	private String card1No;
	private String card2No;
	private String card3No;
	private int bank1Id;
	private int bank2Id;
	private int bank3Id;
	private String comment;
	private int created_at;
	private int updated_at;
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
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getCard1No() {
		return card1No;
	}
	public void setCard1No(String card1No) {
		this.card1No = card1No;
	}
	public String getCard2No() {
		return card2No;
	}
	public void setCard2No(String card2No) {
		this.card2No = card2No;
	}
	public String getCard3No() {
		return card3No;
	}
	public void setCard3No(String card3No) {
		this.card3No = card3No;
	}
	public int getBank1Id() {
		return bank1Id;
	}
	public void setBank1Id(int bank1Id) {
		this.bank1Id = bank1Id;
	}
	public int getBank2Id() {
		return bank2Id;
	}
	public void setBank2Id(int bank2Id) {
		this.bank2Id = bank2Id;
	}
	public int getBank3Id() {
		return bank3Id;
	}
	public void setBank3Id(int bank3Id) {
		this.bank3Id = bank3Id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getCreated_at() {
		return created_at;
	}
	public void setCreated_at(int created_at) {
		this.created_at = created_at;
	}
	public int getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(int updated_at) {
		this.updated_at = updated_at;
	}
	
	

}
