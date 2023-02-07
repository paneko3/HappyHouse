package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AnswerDto;
import com.ssafy.happyhouse.model.dto.AptCommentDto;
import com.ssafy.happyhouse.model.service.AnswerService;
import com.ssafy.happyhouse.model.service.AptCommentService;
import com.ssafy.happyhouse.model.service.jwtService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/aptcomment")
public class AptCommentController {

	private static final Logger logger = LoggerFactory.getLogger(AptCommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AptCommentService aptCommentService;
	
	@Autowired
	private jwtServiceImpl jwtService;

//
//	@ApiOperation(value = "모든 AptComment의 정보를 반환한다.", response = List.class)
//	@GetMapping
//	public ResponseEntity<List<AptCommentDto>> retrieveAptComment() throws Exception {
//		logger.info("retrieveAptComment - 호출");
//		return new ResponseEntity<List<AptCommentDto>>(aptCommentService.selectAptComment(), HttpStatus.OK);
//	}

	@ApiOperation(value = "aptCode번호에 해당하는 AptComment의 정보를 반환한다.", response = List.class)
	@GetMapping("{aptCode}")
	public ResponseEntity<List<AptCommentDto>> detailAptComment(@PathVariable String aptCode) {
		logger.info("detailAptComment - 호출");
		return new ResponseEntity<List<AptCommentDto>>(aptCommentService.detailAptComment(aptCode), HttpStatus.OK);
	
	}

	@ApiOperation(value = "새로운 AptComment 정보를 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeAptComment(@RequestBody AptCommentDto aptCommentDto) {
		logger.info("writeAptComment - 호출");
		if (aptCommentService.writeAptComment(aptCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "commentno번호에 해당하는 AptComment의 정보를 수정한다.", response = String.class)
	@PutMapping("{commentno}")
	public ResponseEntity<String> updateAptComment(@RequestBody AptCommentDto aptCommentDto) {
		logger.info("updateAptComment - 호출");
		logger.debug("" + aptCommentDto);

		if (aptCommentService.updateAptComment(aptCommentDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "AptComment번호에 해당하는 AptComment의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("{commentno}")
	public ResponseEntity<String> deleteAptComment(@PathVariable int commentno) {
		logger.info("deleteAptComment - 호출");
		if (aptCommentService.deleteAptComment(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

}