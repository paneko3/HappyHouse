package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.MemberDto;

@Mapper
public interface MemberMapper {

	public MemberDto idCheck(String id);
	public int registerMember(MemberDto memberDto);
	public int updateUser(MemberDto memberDto);
	public int deleteMember(String id);
	
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public MemberDto userInfo(String id);
	
	public MemberDto findId(MemberDto memberDto);
	public MemberDto findUser(MemberDto memberDto);
}
