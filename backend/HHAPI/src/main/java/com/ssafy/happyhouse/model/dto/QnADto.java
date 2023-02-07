package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "QnADto (QnA정보)", description = "QnA번호, 제목, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class QnADto {
	@ApiModelProperty(value = "QnA번호")
	private int qnano;
	@ApiModelProperty(value = "제목")
	private String subject;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성자아이디")
	private String id;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public QnADto(int qnano, String subject, String content, String id, String regtime) {
		super();
		this.qnano = qnano;
		this.subject = subject;
		this.content = content;
		this.id = id;
		this.regtime = regtime;
	}
	
	
}