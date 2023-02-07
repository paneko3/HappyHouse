package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.AnswerDto;
import com.ssafy.happyhouse.model.mapper.AnswerMapper;


@Service
public class AnswerServiceImpl implements AnswerService {
	
    @Autowired
	private AnswerMapper answerMapper;

  	@Override
  	public boolean writeAnswer(AnswerDto answerDto) {
		return answerMapper.insertAnswer(answerDto) == 1;
	}

	@Override
	public AnswerDto detailAnswer(int answerno) {
		return answerMapper.selectAnswerByNo(answerno);
	}

	@Override
	@Transactional
	public boolean updateAnswer(AnswerDto answerDto) {
		return answerMapper.updateAnswer(answerDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteAnswer(int qnano) {
		return answerMapper.deleteAnswer(qnano) == 1;
	}
}