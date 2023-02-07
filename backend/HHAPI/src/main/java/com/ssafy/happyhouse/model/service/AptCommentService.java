package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.AptCommentDto;
import com.ssafy.happyhouse.model.dto.QnADto;
public interface AptCommentService {
//	public List<AptCommentDto> selectAptComment();
	public List<AptCommentDto> detailAptComment(String aptCode);
	public boolean writeAptComment(AptCommentDto aptCommentDto);
	public boolean updateAptComment(AptCommentDto aptCommentDto);
	public boolean deleteAptComment(int commentno);
}
