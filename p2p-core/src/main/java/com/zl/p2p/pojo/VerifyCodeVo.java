package com.zl.p2p.pojo;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装短信验证信息
 * @author Administrator
 *
 */
@Getter
@Setter
@ToString
public class VerifyCodeVo {
	/**验证码*/
	private String verifyCode;
	/**接收验证码的手机号码*/
	private String phoneNumber;
	/**最后一次发送成功的时间*/
	private Date lastTime;
	private String reason;
	private Integer error_code;
	
}