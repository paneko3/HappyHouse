package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.AnswerDto;
@Mapper
public interface AnswerMapper {
	public AnswerDto selectAnswerByNo(int qnano);
	public int insertAnswer(AnswerDto answerDto);
	public int updateAnswer(AnswerDto answerDto);
	public int deleteAnswer(int qnano);
}