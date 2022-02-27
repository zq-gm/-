package com.zq.springboot.utils;

import java.util.Date;

import org.springframework.util.DigestUtils;

/**
 * @author fmq
 * token管理
 */
public class TokenManager {

	public static String getToken(String password) {
		Date date = new Date();
		String token = DigestUtils.md5DigestAsHex((date.getTime()+password).getBytes());
		return token;
	}
}
