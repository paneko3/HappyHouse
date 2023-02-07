package com.ssafy.happyhouse.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.service.MemberService;
import com.ssafy.happyhouse.model.service.jwtServiceImpl;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemberService memberService;

	@Autowired
	private jwtServiceImpl jwtService;

	@Autowired
	ResourceLoader resLoader;

	
	@ApiOperation(value = "아이디에 해당하는 사용자의 정보를 반환한다.", response = MemberDto.class)
	@GetMapping("{id}")
	public ResponseEntity<MemberDto> idCheck(@PathVariable String id) {
		logger.info("idCheck - 호출");
		return new ResponseEntity<MemberDto>(memberService.userInfo(id), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 회원 정보를 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> register(@RequestBody MemberDto memberDto) {
		logger.debug("register - 호출");
		if (memberService.registerMember(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "아이디에 해당하는 회원의 정보를 수정한다.", response = String.class)
	@PutMapping("{id}")
	public ResponseEntity<String> updateUser(@RequestBody MemberDto memberDto) {
		logger.debug("updateUser - 호출");
		logger.debug("" + memberDto);
		if (memberService.updateUser(memberDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "아이디에 해당하는 사용자의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteMember(@PathVariable String id) {
		logger.debug("deleteMember - 호출");
		if (memberService.deleteMember(id)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	@ApiOperation(value = "아이디와 비밀번호가 유효하면 토큰을 발급하여 로그인 시킨다.", response = String.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		logger.info("로그인 요청");
		try {
			MemberDto loginUser = memberService.login(memberDto);
			if (loginUser != null) {
				String token = jwtService.create("id", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "토큰의 유효성을 검사하고 사용자 정보를 반환한다.", response = String.class)
	@GetMapping("/info/{id}")
	public ResponseEntity<Map<String, Object>> getInfo(@PathVariable("id") String id, HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			logger.info(request.getHeader("access-token"));
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(id);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	

	@ApiOperation(value = "이름과 연락처에 해당하는 사용자의 아이디를 반환한다.", response = String.class)
	@PostMapping("findId")
	public ResponseEntity<String> findId(@RequestBody MemberDto memberDto) {
		logger.info("findID - 호출");
		if (memberService.findId(memberDto) != null) {
			return new ResponseEntity<String>(memberService.findId(memberDto).getId(), HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "아이디와 이름, 이메일에 해당하는 사용자의 비밀번호를 임시비밀번호로 변경한다.", response = String.class)
	@PostMapping("findpass")
	public ResponseEntity<String> findpass(@RequestBody MemberDto memberDto) {
		logger.info("findpass - 호출");
		boolean check = memberService.findPass(memberDto);
		if (check)
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
