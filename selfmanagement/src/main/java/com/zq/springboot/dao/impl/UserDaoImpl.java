package com.zq.springboot.dao.impl;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zq.springboot.commontypes.UserInfo;
import com.zq.springboot.dao.UserDao;

@Mapper
@Service
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSession sqlSession;
	@Override
	public UserInfo getUserByName(String userName) {
		UserInfo userInfo = sqlSession.selectOne("getUserByName", userName);
		return userInfo;
	}

	@Override
	public UserInfo userLogin(UserInfo user) {
		UserInfo userInfo = sqlSession.selectOne("userLogin",user);
		return userInfo;
	}

	@Override
	public boolean userRegist(UserInfo userInfo) {
		return sqlSession.insert("userRegist", userInfo) > 0 ?true: false;
	}

	@Override
	public boolean changeUserInfo(UserInfo userInfo) {
		return sqlSession.update("changeUserInfo", userInfo) > 0 ?true: false;
	}

}
