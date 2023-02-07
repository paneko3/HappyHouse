package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.QnADto;
@Mapper
public interface QnAMapper {
	public List<QnADto> selectQnA();
	public QnADto selectQnAByNo(int qnano);
	public int insertQnA(QnADto qnADto);
	public int updateQnA(QnADto qnADto);
	public int deleteQnA(int qnano);
}