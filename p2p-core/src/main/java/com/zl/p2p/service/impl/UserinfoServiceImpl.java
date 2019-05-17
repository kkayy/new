package com.zl.p2p.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.p2p.mapper.UserinfoMapper;
import com.zl.p2p.pojo.Userinfo;
import com.zl.p2p.service.IUserinfoService;
import com.zl.p2p.service.IVerifyCodeService;


@Service
public class UserinfoServiceImpl implements IUserinfoService {
	@Autowired
	private UserinfoMapper userinfoMapper;
	@Autowired
	private IVerifyCodeService verifyCodeService;

	@Override
	public Userinfo getById(Long id) {
		return userinfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public void bindPhone(String phoneNumber, String verifyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkPhone(String phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
