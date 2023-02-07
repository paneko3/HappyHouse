package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.AnswerDto;
import com.ssafy.happyhouse.model.dto.AptCommentDto;
@Mapper
public interface AptCommentMapper {
//	public List<AptCommentDto> selectAptComment();
	public List<AptCommentDto> selectAptCommentByNo(String aptCode);
	public int insertAptComment(AptCommentDto aptCommentDto);
	public int updateAptComment(AptCommentDto aptCommentDto);
	public int deleteAptComment(int commentno);
}