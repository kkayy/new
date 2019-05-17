package com.zl.p2p.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Realauth implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Long id;
	private String realname;
	private Integer sex;
	private String birthDate;
	private String idNumber;
	private String address;
	private Integer state;
	private String image1;
	private String image2;
	private String remark;
	private Date auditTime;
	private Date applyTime;
	private Long auditor_id;
	private Long applier_id;
	
}
