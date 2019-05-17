package com.zl.p2p.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zl.p2p.exception.LogininfoException;
import com.zl.p2p.mapper.AccountMapper;
import com.zl.p2p.mapper.IplogMapper;
import com.zl.p2p.mapper.LogininfoMapper;
import com.zl.p2p.mapper.UserinfoMapper;
import com.zl.p2p.pojo.Account;
import com.zl.p2p.pojo.Iplog;
import com.zl.p2p.pojo.Logininfo;
import com.zl.p2p.pojo.Userinfo;
import com.zl.p2p.service.ILogininfoService;
import com.zl.p2p.util.MD5;
import com.zl.p2p.util.UserContext;
@Service("logininfoService")
public class LogininfoServiceImpl implements ILogininfoService {
	@Autowired
	private LogininfoMapper logininfoMapper;
	@Autowired
	private IplogMapper iplogMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserinfoMapper userinfoMapper;

	@Override
	public boolean queryByUsernameAndPassword(String username, String password, int usertype, String ip)
			throws LogininfoException {
		// TODO Auto-generated method stub
		Iplog iplog = new Iplog();
		iplog.setIp(ip);
		iplog.setLoginTime(new Date());
		iplog.setUsername(username);
		iplog.setLoginType(usertype);
		iplog.setLoginState(Iplog.LOGINSTATE_FAILED);
		Logininfo logininfo = logininfoMapper.queryByUsernameAndPassword( username, MD5.encode(password) );
		if(logininfo!=null) {
			iplog.setLoginInfoId(logininfo.getId());
			if(logininfo.getState()==Logininfo.STATE_LOCK) {
				iplogMapper.insert(iplog);
				throw new LogininfoException("账户已锁定");
			}
			if(logininfo.getState()==Logininfo.STATE_DELETE) {
				iplogMapper.insert(iplog);
				throw new LogininfoException("账户已注销");
			}
			UserContext.setCurrent(logininfo);
			iplog.setLoginState(Iplog.LOGINSTATE_SUCCESS);
			
		}
		iplogMapper.insert(iplog);
		return logininfo!=null;
	}

	@Override
	public boolean queryByUsernameAndUsertype(String username, int usertypeNormal) {
		Logininfo logininfo = logininfoMapper.query(username,usertypeNormal);
		if(logininfo!=null) {
			return false;
		}
		return true;
	}

	@Override
	public void insert(String username, String password, int usertypeNormal) throws LogininfoException {
		if(queryByUsernameAndUsertype(username,usertypeNormal)==false) {
			throw new RuntimeException("用户已存在");
		}
		Logininfo logininfo = new Logininfo();
		logininfo.setUsername(username);
		logininfo.setPassword(MD5.encode(password));
		logininfo.setUsertype(usertypeNormal);
		logininfoMapper.insert(logininfo);
		//Accont, Userinfo 添加id   --> 刚刚添加Logininfo的id
		Account ac = new Account();
		ac.setId(logininfo.getId());
		accountMapper.insert(ac);
		Userinfo uf = new Userinfo();
		uf.setId(logininfo.getId());
		userinfoMapper.insert(uf);		
	}		
}
