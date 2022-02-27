package com.zq.springboot.dao;

import com.zq.springboot.commontypes.UserInfo;

public interface UserDao {

	UserInfo getUserByName(String userName);
	/**
	 * @param user用户登录信息
	 * @return 登录确认带token信息
	 */
	UserInfo userLogin(UserInfo user);
	/**
	 * @param userInfo 用户注册信息 
	 * @return
	 */
	boolean userRegist(UserInfo userInfo);
	/**
	 * @param userInfo 修改用户信息
	 * @return
	 */
	boolean changeUserInfo(UserInfo userInfo);
	
}
