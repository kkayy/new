package com.zl.p2p.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Iplog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 登录成功
	 */
	public static int  LOGINSTATE_SUCCESS = 0;
	/**
	 * 登录失败
	 */
	public static int  LOGINSTATE_FAILED = 1;
	private Long id;
	private String ip;
	private Integer loginState;
	private String username;
	private Long loginInfoId;
	private Integer loginType;
	private Date loginTime;

}
