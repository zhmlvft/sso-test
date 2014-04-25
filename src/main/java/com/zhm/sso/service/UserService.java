package com.zhm.sso.service;

import com.zhm.sso.model.UserInfo;

public interface UserService {

	UserInfo findUserByCond(String username);

}
