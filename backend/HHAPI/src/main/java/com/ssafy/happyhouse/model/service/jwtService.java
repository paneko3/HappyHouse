package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;


public interface jwtService {
	
	<T> String create(String key, T data, String subject);
	Map<String, Object> get(String key);
	String getUserId();
	boolean checkToken(String jwt);

}
