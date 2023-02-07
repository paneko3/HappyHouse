package com.ssafy.happyhouse.model.dto;

public class DongDto {
	private String dongCode;
	private String dongName;
	private String subName;
	private String lat;
	private String lng;
	public DongDto(String dongCode, String dongName, String subName, String lat, String lng) {
		super();
		this.dongCode = dongCode;
		this.dongName = dongName;
		this.subName = subName;
		this.lat = lat;
		this.lng = lng;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
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
		return "DongDto [dongCode=" + dongCode + ", dongName=" + dongName + ", subName=" + subName + ", lat=" + lat
				+ ", lng=" + lng + "]";
	}

}
