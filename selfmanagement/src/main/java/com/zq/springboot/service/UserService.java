package com.zq.springboot.service;

//import java.util.List;

import com.zq.springboot.common.MessageResult;
import com.zq.springboot.commontypes.UserInfo;


/**
 * @author fmq
 * 用户操作service
 */
public interface UserService {
	
	MessageResult<UserInfo> userLogin(UserInfo userInfo); 

}
