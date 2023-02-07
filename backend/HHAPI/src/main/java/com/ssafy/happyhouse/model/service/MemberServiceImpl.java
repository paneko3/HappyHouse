package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String sender;
	
	
	@Override
	public MemberDto userInfo(String id) {
		return memberMapper.userInfo(id);
	}
	
	@Override
	public boolean checkIdExist(String id) {
		MemberDto memberDto = memberMapper.idCheck(id);
		if(memberDto != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean registerMember(MemberDto memberDto) {
		return memberMapper.registerMember(memberDto) == 1;
	}

	@Override
	public boolean updateUser(MemberDto memberDto) {
		return memberMapper.updateUser(memberDto) == 1;
	}

	@Override
	public boolean deleteMember(String id){
		return memberMapper.deleteMember(id) == 1;
	}
	
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getId() == null || memberDto.getPassword() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public MemberDto idCheck(String id) {
		return memberMapper.idCheck(id);
	}

	
	@Override
	public MemberDto findId(MemberDto memberDto) {
		return memberMapper.findId(memberDto);
	}

	@Override
	public boolean findPass(MemberDto memberDto) {
		// 회원정보 있는지 확인한뒤 있으면 이메일보내고 true 리턴 없으면 false 리턴
		MemberDto tempMemberDto = memberMapper.findUser(memberDto);
		if (tempMemberDto != null) {
			SimpleMailMessage message = new SimpleMailMessage();
			String tmpPassword = getTempPassword();
			memberMapper.updateUser(new MemberDto(tempMemberDto.getId(), tmpPassword, tempMemberDto.getTel(),
					tempMemberDto.getEmail(), tempMemberDto.getName()));
			message.setFrom(sender);
			message.setTo(memberDto.getEmail());
			message.setSubject("해피하우스 임시 비밀번호 발급");
			message.setText("안녕하세요" + memberDto.getName() + "님\n 새로운 임시 비밀번호입니다. : " + tmpPassword);
			mailSender.send(message);
			return true;
		} else
			return false;
	}

	// 임시 비밀번호 발급
	public String getTempPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		String str = "";

		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		return str;
	}

}
