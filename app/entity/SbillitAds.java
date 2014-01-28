package entity;

import java.io.Serializable;

public class SbillitAds implements Serializable {
	
	private Long id;
	private String title;
	private String imageUrl;
	private Long comboId;
	private Long areaId;
	private Long startAt;
	private Long endAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Long getStartAt() {
		return startAt;
	}
	public void setStartAt(Long startAt) {
		this.startAt = startAt;
	}
	public Long getEndAt() {
		return endAt;
	}
	public void setEndAt(Long endAt) {
		this.endAt = endAt;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public Long getComboId() {
		return comboId;
	}
	public void setComboId(Long comboId) {
		this.comboId = comboId;
	}
	
	

}
