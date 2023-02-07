package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.AnswerDto;
import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.dto.QnADto;
@Mapper
public interface NewsMapper {
	public List<NewsDto> selectNews();
	public int updateNews(NewsDto newsDto);
}