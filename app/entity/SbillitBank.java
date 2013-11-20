package entity;

import java.io.Serializable;

public class SbillitBank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int created;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCreated() {
		return created;
	}
	public void setCreated(int created) {
		this.created = created;
	}
	
}
