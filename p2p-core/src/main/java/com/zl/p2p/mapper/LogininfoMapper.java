package com.zl.p2p.mapper;

import org.apache.ibatis.annotations.Param;

import com.zl.p2p.pojo.Logininfo;

public interface LogininfoMapper {

	Logininfo queryByUsernameAndPassword(@Param("username")String username,@Param("password") String password);

	Logininfo query(@Param("username")String username,@Param("usertypeNormal") int usertypeNormal);


	void insert(Logininfo logininfo);

}
