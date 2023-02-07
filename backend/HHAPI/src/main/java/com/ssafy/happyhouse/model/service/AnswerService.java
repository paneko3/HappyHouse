package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.AnswerDto;
public interface AnswerService {
	public AnswerDto detailAnswer(int qnano);
	public boolean writeAnswer(AnswerDto answerDto);
	public boolean updateAnswer(AnswerDto answerDto);
	public boolean deleteAnswer(int qnano);
}
