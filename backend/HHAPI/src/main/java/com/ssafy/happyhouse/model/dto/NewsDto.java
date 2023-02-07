package com.ssafy.happyhouse.model.dto;

public class NewsDto {
	private int no;
	private String title;
	private String content;
	private String regtime;
	private String company;
	private String url;
	private String imgsrc;
	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImgsrc() {
		return imgsrc;
	}

	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}

	public NewsDto(int no, String title, String content, String regtime, String company, String url, String imgsrc) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
		this.company = company;
		this.url = url;
		this.imgsrc = imgsrc;
	}

	@Override
	public String toString() {
		return "NewsDto [no=" + no + ", title=" + title + ", content=" + content + ", regtime=" + regtime + ", company="
				+ company + ", url=" + url + ", imgsrc=" + imgsrc + "]";
	}

	
	
}
