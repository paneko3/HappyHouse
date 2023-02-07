package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.MemberDto;

public interface MemberService {
	public boolean checkIdExist(String id);
	
	public MemberDto idCheck(String id);
	public boolean registerMember(MemberDto memberDto);
	public boolean updateUser(MemberDto memberDto);
	public boolean deleteMember(String id);

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String id);
	
	public MemberDto findId(MemberDto memberDto);
	public boolean findPass(MemberDto memberDto);
	
}
