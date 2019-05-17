package com.zl.p2p.service;

import com.zl.p2p.exception.LogininfoException;

public interface ILogininfoService {

	boolean queryByUsernameAndPassword(String username, String password, int usertypeNormal, String ip) throws LogininfoException;
	boolean queryByUsernameAndUsertype(String username,int usertypeNormal) ;
	void insert(String username, String password, int usertypeNormal) throws LogininfoException;
}
