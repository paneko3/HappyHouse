package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "MemberDto (회원정보)", description = "아이디, 비밀번호, 전화번호, 이메일, 이름을 가진   Domain Class")
public class MemberDto {
	@ApiModelProperty(value = "아이디")
	private String id;
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "전화번호")
	private String tel;
	@ApiModelProperty(value = "이메일")
	private String email;
	@ApiModelProperty(value = "이름")
	private String name;

	public MemberDto() {
	}

	public MemberDto(String id, String password, String tel, String email, String name) {
		super();
		this.id = id;
		this.password = password;
		this.tel = tel;
		this.email = email;
		this.name = name;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", password=" + password + ", tel=" + tel + ", email=" + email + ", name=" + name
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
