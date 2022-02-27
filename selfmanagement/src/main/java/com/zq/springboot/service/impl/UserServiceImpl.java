package com.zq.springboot.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.zq.springboot.common.MessageResult;
import com.zq.springboot.commontypes.UserInfo;
import com.zq.springboot.dao.UserDao;
import com.zq.springboot.service.UserService;
import com.zq.springboot.utils.TokenManager;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@Override
	public MessageResult<UserInfo> userLogin(UserInfo userInfo) {
		MessageResult<UserInfo> result = new MessageResult<UserInfo>();
		UserInfo user = userDao.userLogin(userInfo);
		if (user == null) {
			result.setMessage("用户登录失败");
			result.setSuccessed(false);
		} else {
			String token = UUID.randomUUID().toString().replaceAll("-", "");
			//将用户的ID信息存入redis缓存，并设置一小时的过期时间
			stringRedisTemplate.opsForValue().set(token, user.getUserId(),3600,TimeUnit.SECONDS);
			user.setToken(token);
			result.setData(user);
			result.setMessage("登录成功");
			result.setSuccessed(true);
		}
		return result;
	}


}
