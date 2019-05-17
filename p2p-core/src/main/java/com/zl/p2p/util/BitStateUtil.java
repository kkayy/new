package com.zl.p2p.util;

public class BitStateUtil {
	/**
	 * 实名制认证的状态码
	 */
	public  static  final  long OP_REAL_AUTH = 1 << 0;  //0001
	/**
	 * 手机认证的状态码
	 */
	public  static  final  long OP_PHONE_AUTH = 1 << 1; //0010
	/**
	 * 邮箱认证的状态码
	 */
	public  static  final  long OP_MAIL_AUTH = 1 << 2;  //0100
	/**
	 * VIP的状态码
	 */
	public  static  final  long OP_VIP = 1 << 3; //1000
	
	/**
	 * 判断是否有该认证
	 * @param bitState  用户认证状态
	 * @param stateCode  认证状态码
	 * @return   true  有   false 没有
	 */
	public static boolean  hasState(long bitState,  long  stateCode){
		return (bitState & stateCode) > 0;
	}
	
	/**
	 * 添加状态码
	 * @param bitState    用户认证状态
	 * @param stateCode   需要添加认证状态码
	 * @return   添加之后的状态
	 */
	public  static  long  addState(long bitState,  long  stateCode){
		if(hasState(bitState,stateCode)){
			return bitState;
		}
		return  bitState | stateCode;
	}
	

	/**
	 * 移除状态码
	 * @param bitState    用户认证状态
	 * @param stateCode   需要移除认证状态码
	 * @return   移除之后的状态
	 */
	public  static  long  removeState(long bitState,  long  stateCode){
		if(hasState(bitState,stateCode)){
			return bitState ^ stateCode;
		}
		return bitState;
	}
}	
