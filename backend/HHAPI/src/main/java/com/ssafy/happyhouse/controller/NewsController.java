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
import com.ssafy.happyhouse.model.dto.NewsDto;
import com.ssafy.happyhouse.model.service.AnswerService;
import com.ssafy.happyhouse.model.service.AptCommentService;
import com.ssafy.happyhouse.model.service.NewsService;
import com.ssafy.happyhouse.model.service.jwtService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/news")
public class NewsController {

	private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NewsService newsService;
	
	@Autowired
	private jwtServiceImpl jwtService;


	@ApiOperation(value = "전체 뉴스를 반환한다.", response = List.class)
	@GetMapping("")
	public ResponseEntity<List<NewsDto>> retrieveNews() {
		logger.info("retrieveNews - 호출");
		return new ResponseEntity<List<NewsDto>>(newsService.retrieveNews(), HttpStatus.OK);
	
	}


	@ApiOperation(value = "전체 뉴스를 갱신한다.", response = String.class)
	@PutMapping("")
	public ResponseEntity<String> updateNews() {
		logger.info("updateNews - 호출");

		if (newsService.updateNews()) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}



}