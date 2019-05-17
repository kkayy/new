package com.zl.p2p.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

import com.zl.p2p.util.SystemConst;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Account implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	/**对象版本信息*/
	private Integer version = 0; 
	/**交易密码*/
	private String tradePassword;
	/**可用金额*/
	private BigDecimal usableAmount = SystemConst.ZERO;
	/**冻结金额*/
	private BigDecimal freezedAmount = SystemConst.ZERO;
	/**代收利息*/
	private BigDecimal unReceiveInterest = SystemConst.ZERO;
	/**代收本金*/
	private BigDecimal unReceivePrincipal = SystemConst.ZERO;
	/**待还本息*/
	private BigDecimal unReturnAmount = SystemConst.ZERO;
	/**剩余额度*/
	private BigDecimal remainBorrowLimit = SystemConst.INITIAL_BORROW_LIMIT;
	/**授信额度*/
	private BigDecimal borrowLimitAmount = SystemConst.INITIAL_BORROW_LIMIT;
	
}
