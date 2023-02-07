package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

public class AptCommentDto {
	private int commentno;
	private String id;
	private String content;
	private String regtime;
	private String aptCode;
	private String rank;
	
	public int getCommentno() {
		return commentno;
	}
	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getAptCode() {
		return aptCode;
	}
	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public AptCommentDto(int commentno, String id, String content, String regtime, String aptCode, String rank) {
		super();
		this.commentno = commentno;
		this.id = id;
		this.content = content;
		this.regtime = regtime;
		this.aptCode = aptCode;
		this.rank = rank;
	}
	@Override
	public String toString() {
		return "AptCommentDto [commentno=" + commentno + ", id=" + id + ", content=" + content + ", regtime=" + regtime
				+ ", aptCode=" + aptCode + ", rank=" + rank + "]";
	}

	
}