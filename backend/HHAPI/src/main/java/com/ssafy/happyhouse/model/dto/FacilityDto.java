package com.ssafy.happyhouse.model.dto;

public class FacilityDto {
	private String code;
	private String category;
	private String name;
	private String dongcode;
	private String dongname;
	private String lat;
	private String lng;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDongcode() {
		return dongcode;
	}
	public void setDongcode(String dongcode) {
		this.dongcode = dongcode;
	}
	public String getDongname() {
		return dongname;
	}
	public void setDongname(String dongname) {
		this.dongname = dongname;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "FacilityDto [code=" + code + ", category=" + category + ", name=" + name + ", dongcode=" + dongcode
				+ ", dongname=" + dongname + ", lat=" + lat + ", lng=" + lng + "]";
	}
	
	
	
}
