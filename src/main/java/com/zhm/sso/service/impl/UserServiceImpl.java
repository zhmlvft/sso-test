package com.zhm.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhm.sso.dao.UserDao;
import com.zhm.sso.model.UserInfo;
import com.zhm.sso.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public UserInfo findUserByCond(String username) {
		// TODO Auto-generated method stub
		return userDao.select(username);
	}
	
}
