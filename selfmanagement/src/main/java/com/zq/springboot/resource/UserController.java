package com.zq.springboot.resource;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zq.springboot.common.MessageResult;
import com.zq.springboot.commontypes.UserInfo;
import com.zq.springboot.service.UserService;
import com.zq.springboot.utils.TokenManager;


@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	@RequestMapping("test")
	public String test(){
		return "hello!";
	}
	
	@PostMapping(path="/login")
	public MessageResult<UserInfo> userLogin(@RequestBody UserInfo userInfo) {
		MessageResult<UserInfo> result = new MessageResult<UserInfo>();
		if (userInfo == null || userInfo.getPassword() == null || userInfo.getUsername() == null) {
			result.setMessage("登录名或密码不能为空");
			result.setSuccessed(false);
		} else {
			result = userService.userLogin(userInfo);
		}
		return result;
	}
//	用户注册暂时不做要求
//	@PostMapping(path="/regist")
//	public MessageResult<UserInfo> userRegist(@RequestBody UserInfo userInfo) {
//		MessageResult<UserInfo> result = new MessageResult<UserInfo>();
//		if (userInfo == null || userInfo.getPassword() == null || userInfo.getUsername() == null) {
//			result.setMessage("用户名或密码不能为空");
//			result.setSuccessed(false);
//		} else {
//			result = userService.userLogin(userInfo);
//		}
//		return result;
//	}

}
