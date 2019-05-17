package com.zl.p2p.pojo;

import java.io.Serializable;

import com.zl.p2p.util.BitStateUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Userinfo implements Serializable {

private static final long serialVersionUID = 1L;
	private Long id;
	/**版本号*/
	private Integer version = 0;
	/**用户状态值*/
	private Long bitState = 0L;
	/**用户实名值*/
	private String realName;
	/**用户身份证号*/
	private String idNumber;
	/**用户电话*/
	private String phoneNumber;	
	/**收入*/
	private SystemDictionaryItem incomeGrade;
	/**婚姻情况*/
	private SystemDictionaryItem marriage;
	/**子女情况*/
	private SystemDictionaryItem  kidCount;	
	/**学历*/
	private SystemDictionaryItem educationBackground;
	/**住房条件*/
	private SystemDictionaryItem houseCondition;	
	
	/**绑定的邮箱*/
	private String email;
	
	/**风控资料分数*/
	private Integer authScore = 0;
	
	/**
	 * 实名制信息
	 */
	private Long realauthid;
	/**
	 * 是否进行实名制认证
	 * @return
	 */
	public boolean getIsRealAuth(){
		return BitStateUtil.hasState(bitState, BitStateUtil.OP_REAL_AUTH);
	}
	/**
	 * 是否进行手机号码认证
	 * @return
	 */
	public boolean getIsPhoneAuth(){
		return BitStateUtil.hasState(bitState, BitStateUtil.OP_PHONE_AUTH);
	}
	/**
	 * 是否进行邮箱认证
	 * @return
	 */
	public boolean getIsMailAuth(){
		return BitStateUtil.hasState(bitState, BitStateUtil.OP_MAIL_AUTH);
	}
	/**
	 * 是否是VIP
	 * @return
	 */
	public boolean getIsVIP(){
		return BitStateUtil.hasState(bitState, BitStateUtil.OP_VIP);
	}
	
	/**
	 * 判断是否存在某个状态
	 * @param stateCode
	 * @return
	 */
	public boolean hasState(long stateCode){
		return BitStateUtil.hasState(bitState, stateCode);
	}

	/**
	 * 添加某个状态
	 * @param stateCode
	 * @return
	 */
	public long addState(long stateCode){
		return BitStateUtil.addState(bitState, stateCode);
	} 
	
	/**
	 * 移除某个状态
	 * @param stateCode
	 * @return
	 */
	public long removeState(long stateCode){
		return BitStateUtil.removeState(bitState, stateCode);
	}
}
