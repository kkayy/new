package com.zl.p2p.mapper;

import com.zl.p2p.pojo.Account;

public interface AccountMapper {

	void insert(Account ac);

	Account queryById(Long id);

}
