package com.zl.p2p.service;

import com.zl.p2p.pojo.Userinfo;

public interface IUserinfoService {

	Userinfo getById(Long id);

	void bindPhone(String phoneNumber, String verifyCode);

	boolean checkPhone(String phoneNumber);
	
}
