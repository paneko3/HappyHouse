package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.QnADto;
public interface QnAService {
	public List<QnADto> retrieveQnA();
	public QnADto detailQnA(int qnano);
	public boolean writeQnA(QnADto qnADto);
	public boolean updateQnA(QnADto qnADto);
	public boolean deleteQnA(int qnano);
}
