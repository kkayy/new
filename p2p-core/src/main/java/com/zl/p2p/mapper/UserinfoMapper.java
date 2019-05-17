package com.zl.p2p.mapper;

import org.apache.ibatis.annotations.Param;

import com.zl.p2p.pojo.Userinfo;

public interface UserinfoMapper {

	void insert(Userinfo userinfo);

	
	Userinfo selectByPrimaryKey(Long id);
	
	int updateByPrimaryKey(Userinfo userinfo);


	/**
	 * 查询该手机号码出现的次数
	 * @param phoneNumber
	 * @return
	 */
	int getCountByPhoneNumber(String phoneNumber);


	Userinfo queryById(@Param("id")Long id);
}
