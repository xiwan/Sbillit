package entity;

import java.io.Serializable;

public class SbillitAlarm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int alarm1Status;
	private int alarm2Status;
	private int alarm3Status;
	private int alarm1At;
	private int alarm2At;
	private int alarm3At;
	private int createdAt;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAlarm1Status() {
		return alarm1Status;
	}
	public void setAlarm1Status(int alarm1Status) {
		this.alarm1Status = alarm1Status;
	}
	public int getAlarm2Status() {
		return alarm2Status;
	}
	public void setAlarm2Status(int alarm2Status) {
		this.alarm2Status = alarm2Status;
	}
	public int getAlarm3Status() {
		return alarm3Status;
	}
	public void setAlarm3Status(int alarm3Status) {
		this.alarm3Status = alarm3Status;
	}
	public int getAlarm1At() {
		return alarm1At;
	}
	public void setAlarm1At(int alarm1At) {
		this.alarm1At = alarm1At;
	}
	public int getAlarm2At() {
		return alarm2At;
	}
	public void setAlarm2At(int alarm2At) {
		this.alarm2At = alarm2At;
	}
	public int getAlarm3At() {
		return alarm3At;
	}
	public void setAlarm3At(int alarm3At) {
		this.alarm3At = alarm3At;
	}
	public int getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(int createdAt) {
		this.createdAt = createdAt;
	}
	
}
