package com.zl.p2p.service;

import com.zl.p2p.pojo.Account;
import com.zl.p2p.pojo.Userinfo;

public interface IPersonalService {


	Userinfo queryUserinfoById(Long id);

	Account queryAccountById(Long id);

}
