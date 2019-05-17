package com.zl.p2p.pojo;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Logininfo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//创建静态常量
		/**正常状态*/
		public static  final int  STATE_NORMAL = 0; 
		/**锁定状态*/
		public static  final int  STATE_LOCK = 1;
		/**已删除状态*/
		public static  final int  STATE_DELETE = -1;
		/**前台用户*/
		public static  final int  USERTYPE_NORMAL = 0;
		/**后台用户*/
		public static  final int  USERTYPE_SYSTEM = 1;
	private Long id;
	private String username;
	private String password;
	private Integer state = STATE_NORMAL;
	private Integer usertype;
}
