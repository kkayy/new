package com.zl.p2p.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.p2p.mapper.AccountMapper;
import com.zl.p2p.mapper.UserinfoMapper;
import com.zl.p2p.pojo.Account;
import com.zl.p2p.pojo.Userinfo;
import com.zl.p2p.service.IPersonalService;
@Service
public class PersonalService implements IPersonalService {
@Autowired
private UserinfoMapper userinfoMapper;
@Autowired
private AccountMapper accountMapper;
@Override
public Userinfo queryUserinfoById(Long id) {
	return userinfoMapper.queryById(id);
	 
	
}

@Override
public Account queryAccountById(Long id) {
	// TODO Auto-generated method stub
	return accountMapper.queryById(id);
}
}
