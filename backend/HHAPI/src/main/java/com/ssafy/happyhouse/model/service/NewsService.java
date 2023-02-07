package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.AnswerDto;
import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.dto.QnADto;

public interface NewsService {
	public List<NewsDto> retrieveNews();
	public boolean updateNews();
}
