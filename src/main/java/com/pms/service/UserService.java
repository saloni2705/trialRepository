package com.pms.service;

import com.pms.entity.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email) ;
	

	

}