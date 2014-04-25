package com.zhm.sso.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.zhm.sso.dao.UserDao;
import com.zhm.sso.model.UserInfo;
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<UserInfo,String> implements UserDao {

	public UserDaoImpl()
	{
		super(UserInfo.class);
	}
	
}
