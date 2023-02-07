package com.ssafy.happyhouse.model.dto;

public class StationDto {
	private int code;
	private String line;
	private String name;
	private String lat;
	private String lng;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "StationDto [code=" + code + ", line=" + line + ", name=" + name + ", lat=" + lat + ", lng=" + lng + "]";
	}
	public StationDto(int code, String line, String name, String lat, String lng) {
		super();
		this.code = code;
		this.line = line;
		this.name = name;
		this.lat = lat;
		this.lng = lng;
	}
	
	
}
