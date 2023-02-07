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
import com.ssafy.happyhouse.model.dto.QnADto;
import com.ssafy.happyhouse.model.service.AnswerService;
import com.ssafy.happyhouse.model.service.QnAService;
import com.ssafy.happyhouse.model.service.jwtService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnAController {

	private static final Logger logger = LoggerFactory.getLogger(QnAController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private QnAService qnAService;
	
	@Autowired
	private jwtServiceImpl jwtService;

	@Autowired
	private AnswerService answerService;

	@ApiOperation(value = "모든 QnA의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<QnADto>> retrieveQnA() throws Exception {
		logger.info("retrieveQnA - 호출");
		return new ResponseEntity<List<QnADto>>(qnAService.retrieveQnA(), HttpStatus.OK);
	}

	@ApiOperation(value = "QnA번호에 해당하는 QnA의 정보를 반환한다.", response = QnADto.class)
	@GetMapping(path = "{qnano}", headers = "access-token")
	public ResponseEntity<QnADto> detailQnA(@PathVariable int qnano, @RequestHeader("access-token") String access) {
		logger.info("detailQnA - 호출");
		try {
			if(jwtService.checkToken(access))
			logger.info("성공");
			

			
		}catch (Exception e){
			logger.info("실패");
			return new ResponseEntity<QnADto>(qnAService.detailQnA(qnano), HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<QnADto>(qnAService.detailQnA(qnano), HttpStatus.OK);
	
	}

	@ApiOperation(value = "새로운 QnA 정보를 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeQnA(@RequestBody QnADto qnADto) {
		logger.info("writeQnA - 호출");

		if (qnAService.writeQnA(qnADto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "QnA번호에 해당하는 QnA의 정보를 수정한다.", response = String.class)
	@PutMapping("{qnano}")
	public ResponseEntity<String> updateQnA(@RequestBody QnADto qnADto) {
		logger.info("updateQnA - 호출");
		logger.debug("" + qnADto);

		if (qnAService.updateQnA(qnADto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "QnA번호에 해당하는 QnA의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("{qnano}")
	public ResponseEntity<String> deleteQnA(@PathVariable int qnano) {
		logger.info("deleteQnA - 호출");
		if (qnAService.deleteQnA(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	
	@ApiOperation(value = "새로운 Answer 정보를 입력한다.", response = String.class)
	@PostMapping("{qnano}/answer")
	public ResponseEntity<String> writeAnswer(@RequestBody AnswerDto answerDto) {
		logger.debug("writeAnswer - 호출");
		if (answerService.writeAnswer(answerDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Answer번호에 해당하는 Answer의 정보를 반환한다.", response = AnswerDto.class)
	@GetMapping("{qnano}/answer")
	public ResponseEntity<AnswerDto> detailAnswer(@PathVariable int qnano) {
		logger.debug("detailAnswer - 호출");
		return new ResponseEntity<AnswerDto>(answerService.detailAnswer(qnano), HttpStatus.OK);
	}

	@ApiOperation(value = "Answer번호에 해당하는 Answer의 정보를 수정한다.", response = String.class)
	@PutMapping("{qnano}/answer")
	public ResponseEntity<String> updateAnswer(@RequestBody AnswerDto answerDto) {
		logger.debug("updateAnswer - 호출");
		logger.debug("" + answerDto);

		if (answerService.updateAnswer(answerDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "Answer번호에 해당하는 Answer의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("{qnano}/answer")
	public ResponseEntity<String> deleteAnswer(@PathVariable int qnano) {
		logger.debug("deleteAnswer - 호출");
		if (answerService.deleteAnswer(qnano)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}