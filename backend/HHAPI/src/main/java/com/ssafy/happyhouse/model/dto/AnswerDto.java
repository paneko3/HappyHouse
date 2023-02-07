package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "AnswerDto (답변정보)", description = "답변번호, QnA번호, 내용, 작성자아이디, 작성일을 가진   Domain Class")
public class AnswerDto {
	@ApiModelProperty(value = "답변번호")
	private int answerno;
	@ApiModelProperty(value = "QnA번호")
	private int qnano;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성자아이디")
	private String id;
	@ApiModelProperty(value = "작성일")
	private String regtime;
	public int getAnswerno() {
		return answerno;
	}
	public void setAnswerno(int answerno) {
		this.answerno = answerno;
	}
	public int getQnano() {
		return qnano;
	}
	public void setQnano(int qnano) {
		this.qnano = qnano;
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
	public AnswerDto(int answerno, int qnano, String content, String id, String regtime) {
		super();
		this.answerno = answerno;
		this.qnano = qnano;
		this.content = content;
		this.id = id;
		this.regtime = regtime;
	}

	
}