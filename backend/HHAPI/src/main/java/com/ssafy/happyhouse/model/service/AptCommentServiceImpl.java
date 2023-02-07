package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.dto.AptCommentDto;
import com.ssafy.happyhouse.model.dto.AptCommentDto;
import com.ssafy.happyhouse.model.mapper.AptCommentMapper;


@Service
public class AptCommentServiceImpl implements AptCommentService {
	
    @Autowired
	private AptCommentMapper aptCommentMapper;

//    @Override
//    public List<AptCommentDto> selectAptComment() {
//		return aptCommentMapper.selectAptComment();
//	}
    
  	@Override
  	public boolean writeAptComment(AptCommentDto aptCommentDto) {
		return aptCommentMapper.insertAptComment(aptCommentDto) == 1;
	}

	@Override
	public List<AptCommentDto> detailAptComment(String aptCode) {
		return aptCommentMapper.selectAptCommentByNo(aptCode);
	}

	@Override
	@Transactional
	public boolean updateAptComment(AptCommentDto AptCommentDto) {
		return aptCommentMapper.updateAptComment(AptCommentDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteAptComment(int commentno) {
		return aptCommentMapper.deleteAptComment(commentno) == 1;
	}
}