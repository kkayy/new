package com.zl.p2p.service.impl;



import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.validation.ValidationUtils;

import com.zl.p2p.pojo.VerifyCodeVo;
import com.zl.p2p.service.IVerifyCodeService;
import com.zl.p2p.util.DateUtil;
import com.zl.p2p.util.VerifyCodeUtil;

@Service
public class VerifyCodeServiceImpl implements IVerifyCodeService {

	@Override
	public void sendVerifyCode(String phoneNumber) {
		VerifyCodeVo vo = new VerifyCodeVo();
		if(vo==null||DateUtil.secondBetwen(vo.getLastTime(), new Date())>90) {
			VerifyCodeUtil.sendMsg(phoneNumber);
		}else {
			throw new RuntimeException("操作过于频繁");
		}
		
	}


}
