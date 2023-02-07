package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.mapper.QnAMapper;


@Service
public class QnAServiceImpl implements QnAService {
	
    @Autowired
	private QnAMapper qnAMapper;

    @Override
	public List<QnADto> retrieveQnA() {
		return qnAMapper.selectQnA();
	}
    
  	@Override
  	public boolean writeQnA(QnADto qnADto) {
		return qnAMapper.insertQnA(qnADto) == 1;
	}

	@Override
	public QnADto detailQnA(int qnano) {
		return qnAMapper.selectQnAByNo(qnano);
	}

	@Override
	@Transactional
	public boolean updateQnA(QnADto qnADto) {
		return qnAMapper.updateQnA(qnADto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQnA(int qnano) {
		return qnAMapper.deleteQnA(qnano) == 1;
	}
}